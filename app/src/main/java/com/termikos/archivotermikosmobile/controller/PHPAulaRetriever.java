package com.termikos.archivotermikosmobile.controller;

import android.app.Activity;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.termikos.archivotermikosmobile.R;
import com.termikos.archivotermikosmobile.adapters.CardAdapter;
import com.termikos.archivotermikosmobile.model.Aula;
import com.termikos.archivotermikosmobile.model.AulaEntry;
import com.termikos.archivotermikosmobile.model.ElementoTarjeta;
import com.termikos.archivotermikosmobile.strategy.EntryStrategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PHPAulaRetriever implements Runnable {

    private RecyclerView recyclerView;
    private int aula;
    private Context context;
    public PHPAulaRetriever(RecyclerView recyclerView, int aula, Context context) {
        this.recyclerView = recyclerView;
        this.aula = aula;
        this.context = context;
    }

    @Override
    public void run() {
        List<String> URLs = List.of(
                "https://www.iesarroyoharnina.es/archivoTermikos/aulas/aula1/index.php",
                "https://www.iesarroyoharnina.es/archivoTermikos/aulas/aula2/index.php",
                "https://www.iesarroyoharnina.es/archivoTermikos/aulas/aula3/index.php"
        );
        HttpURLConnection urlConnection = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(URLs.get(aula));
            urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
        Document doc = Jsoup.parse(sb.toString());
        Element tempElement = doc.select("td:contains(Temperatura)").next().first();
        Element humElement = doc.select("td:contains(Humedad)").next().first();
        Element airQualityElement = doc.select("td:contains(Calidad del Aire)").next().first();
        Element gasesElement = doc.select("td:contains(Gases Peligrosos)").next().first();
        Element updateElement = doc.select("td:contains(Última Actualizaciónㅤ)").next().first();
        String title = doc.title();
        float temperatura = tempElement != null ? Float.parseFloat(tempElement.text().split(" ")[0]) : 0f;
        float humedad = humElement != null ? Float.parseFloat(humElement.text().split(" ")[0]) : 0f;
        int calidadaire = airQualityElement != null ? Integer.parseInt(airQualityElement.text().split(" ")[0]) : 0;
        int gasespeligrosos = gasesElement != null ? Integer.parseInt(gasesElement.text().split(" ")[0]) : 0;
        LocalDateTime ultimaActualizacion = updateElement != null ? LocalDateTime.parse(updateElement.text().replace(" ", "T")) : LocalDateTime.now();

        ElementoTarjeta temperaturaCard = new ElementoTarjeta("Temperatura", temperatura + " ºC", R.drawable.temperatura);
        ElementoTarjeta humedadCard = new ElementoTarjeta("Humedad", humedad + " %", R.drawable.humedad);
        ElementoTarjeta calidadAireCard = new ElementoTarjeta("Calidad del Aire", calidadaire + " ppm", R.drawable.aire);
        ElementoTarjeta gasesCard = new ElementoTarjeta("Gases Peligrosos", gasespeligrosos + " ppm", R.drawable.gasespeligrosos);
        ElementoTarjeta ultimaActualizacionCard = new ElementoTarjeta("Última Actualización", ultimaActualizacion.toString(), R.drawable.calendario);

        List<ElementoTarjeta> cards = List.of(temperaturaCard, humedadCard, calidadAireCard, gasesCard, ultimaActualizacionCard);
        ((Activity) context).runOnUiThread(() -> recyclerView.setAdapter(new CardAdapter(cards, context,R.layout.minicard, new EntryStrategy())));
    }
}
