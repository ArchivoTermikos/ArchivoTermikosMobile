package com.termikos.archivotermikosmobile.model;

import com.termikos.archivotermikosmobile.strategy.recomendaciones.RecomendacionStrategy;

public class ElementoTarjetaDato extends ElementoTarjeta {

    private String[] recomendaciones;
    private RecomendacionStrategy recomendacionStrategy;

    public ElementoTarjetaDato(String title, String subtitle, int current, String[] recomendaciones, RecomendacionStrategy recomendacionStrategy) {
        super(title, subtitle, current);
        this.recomendaciones = recomendaciones;
        this.recomendacionStrategy = recomendacionStrategy;

    }

    public String getRecomendacion(){
        return recomendacionStrategy.getCurrentRecomendacion(recomendaciones, Double.parseDouble(subtitle.split(" ")[0]));
    }
}
