package com.termikos.archivotermikosmobile.strategy.recomendaciones;

public class RecomendacionStrategyTemperatura implements RecomendacionStrategy {
    @Override
    public String getCurrentRecomendacion(String[] recomendaciones, double valor) {
        if (valor < 19) {
            return recomendaciones[0];
        } else if (valor > 25) {
            return recomendaciones[1];
        } else {
            return recomendaciones[2];
        }
    }
}
