package com.termikos.archivotermikosmobile.strategy.recomendaciones;

public class RecomendacionStrategyHumedad implements RecomendacionStrategy {
    @Override
    public String getCurrentRecomendacion(String[] recomendaciones, double valor) {
        if (valor < 45) {
            return recomendaciones[0];
        } else if (valor > 60) {
            return recomendaciones[1];
        } else {
            return recomendaciones[2];
        }
    }
}
