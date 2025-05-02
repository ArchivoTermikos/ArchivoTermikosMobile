package com.termikos.archivotermikosmobile.strategy.recomendaciones;

public class RecomendacionStrategyAire implements RecomendacionStrategy {
    @Override
    public String getCurrentRecomendacion(String[] recomendaciones, double valor) {
        if (valor < 200) {
            return recomendaciones[0];
        } else if (valor < 400) {
            return recomendaciones[1];
        } else if (valor < 1000) {
            return recomendaciones[2];
        } else if (valor < 2000) {
            return recomendaciones[3];
        } else {
            return recomendaciones[4];
        }
    }
}
