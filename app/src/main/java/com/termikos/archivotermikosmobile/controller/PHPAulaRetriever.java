package com.termikos.archivotermikosmobile.controller;

import com.termikos.archivotermikosmobile.model.Aula;
import com.termikos.archivotermikosmobile.model.AulaEntry;

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

    @Override
    public void run() {
        List<String> URLs = List.of(
                "https://www.iesarroyoharnina.es/archivoTermikos/aulas/aula1/index.php",
                "https://www.iesarroyoharnina.es/archivoTermikos/aulas/aula2/index.php",
                "https://www.iesarroyoharnina.es/archivoTermikos/aulas/aula3/index.php"
        );
        List<String> collect = URLs.stream().map(u -> {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(u);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                urlConnection.disconnect();
            }
        }).collect(Collectors.toList());
        List<Aula> aulas = collect.stream().map(c -> {
            Document doc = Jsoup.parse(c);
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
            List<AulaEntry> entries = List.of(
                    new AulaEntry(temperatura, humedad, calidadaire, gasespeligrosos, ultimaActualizacion)
            );
            Aula aula = new Aula(title, entries);
            System.out.println(aula);
            return aula;
        }).collect(Collectors.toList());
    }
}
