package com.termikos.archivotermikosmobile.controller;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.termikos.archivotermikosmobile.R;
import com.termikos.archivotermikosmobile.adapters.CardAdapter;
import com.termikos.archivotermikosmobile.adapters.CardAdapterData;
import com.termikos.archivotermikosmobile.model.Aula;
import com.termikos.archivotermikosmobile.model.AulaEntry;
import com.termikos.archivotermikosmobile.model.ElementoTarjeta;
import com.termikos.archivotermikosmobile.model.ElementoTarjetaDato;
import com.termikos.archivotermikosmobile.strategy.EntryStrategy;
import com.termikos.archivotermikosmobile.strategy.recomendaciones.RecomendacionStrategyAire;
import com.termikos.archivotermikosmobile.strategy.recomendaciones.RecomendacionStrategyGases;
import com.termikos.archivotermikosmobile.strategy.recomendaciones.RecomendacionStrategyHumedad;
import com.termikos.archivotermikosmobile.strategy.recomendaciones.RecomendacionStrategyTemperatura;

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
    boolean check = true;

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
            Log.e("PHPAulaRetriever", "Error al conectar con la URL", e);
            check = false;
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

        String[] recomendacionesTemperatura = new String[]{context.getString(R.string.temperaturaLT19), context.getString(R.string.temperaturaGT25), context.getString(R.string.temperaturaDEF)};
        String[] recomendacionesHumedad = new String[]{context.getString(R.string.humedadLT45), context.getString(R.string.humedadGT60), context.getString(R.string.humedadDEF)};
        String[] recomendacionesCalidadAire = new String[]{context.getString(R.string.calLT200), context.getString(R.string.calLT400), context.getString(R.string.calLT1000), context.getString(R.string.calLT2000), context.getString(R.string.calGT2000)};
        String[] recomendacionesGases = new String[]{context.getString(R.string.gasesLT30), context.getString(R.string.gasesLT80), context.getString(R.string.gasesLT150), context.getString(R.string.gasesLT300), context.getString(R.string.gasesGT300)};
        ElementoTarjeta temperaturaCard = new ElementoTarjetaDato("Temperatura", temperatura + " ºC", R.drawable.temperatura, recomendacionesTemperatura, new RecomendacionStrategyTemperatura());
        ElementoTarjeta humedadCard = new ElementoTarjetaDato("Humedad", humedad + " %", R.drawable.humedad, recomendacionesHumedad, new RecomendacionStrategyHumedad());
        ElementoTarjeta calidadAireCard = new ElementoTarjetaDato("Calidad del Aire", calidadaire + " ppm", R.drawable.aire, recomendacionesCalidadAire, new RecomendacionStrategyAire());
        ElementoTarjeta gasesCard = new ElementoTarjetaDato("Gases Peligrosos", gasespeligrosos + " ppm", R.drawable.gasespeligrosos, recomendacionesGases, new RecomendacionStrategyGases());
        ElementoTarjeta ultimaActualizacionCard = new ElementoTarjeta("Última Actualización", ultimaActualizacion.toString().replace("T", "   "), R.drawable.calendario);

        List<ElementoTarjeta> cards;
        if (!check) {
            cards = List.of(new ElementoTarjeta("Error", "No se ha podido conectar con el servidor", R.drawable.cargando));
        } else {
            cards = List.of(temperaturaCard, humedadCard, calidadAireCard, gasesCard, ultimaActualizacionCard);
        }
        cards.forEach(System.out::println);
        System.out.println("aaaaaa");
        ((Activity) context).runOnUiThread(() -> recyclerView.setAdapter(new CardAdapterData(cards, context, R.layout.minicard, new EntryStrategy())));
    }
}
