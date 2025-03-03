package com.termikos.archivotermikosmobile.model;

import java.time.LocalDateTime;

public class AulaEntry {
    private float temperatura;
    private float humedad;
    private int calidadAire;
    private int gasesPeligrosos;
    private LocalDateTime fechaHora;

    public AulaEntry(float temperatura, float humedad, int gasesPeligrosos, int calidadAire, LocalDateTime fechaHora) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.calidadAire = calidadAire;
        this.gasesPeligrosos = gasesPeligrosos;
        this.fechaHora = fechaHora;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public float getHumedad() {
        return humedad;
    }

    public int getCalidadAire() {
        return calidadAire;
    }

    public int getGasesPeligrosos() {
        return gasesPeligrosos;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return "AulaEntry{" +
                "temperatura=" + temperatura +
                ", humedad=" + humedad +
                ", calidadAire=" + calidadAire +
                ", gasesPeligrosos=" + gasesPeligrosos +
                ", fechaHora=" + fechaHora +
                '}';
    }
}
