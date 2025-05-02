package com.termikos.archivotermikosmobile.strategy.recomendaciones;

public class RecomendacionStrategyGases implements RecomendacionStrategy {
    @Override
    public String getCurrentRecomendacion(String[] recomendaciones, double valor) {
        long tiempoVentilacion = Math.round(138.6/(1.905 * 5 * 10));
        if (valor < 30) {
            return recomendaciones[0];
        } else if (valor < 80) {
            return recomendaciones[1];
        } else if (valor < 150) {
            if (tiempoVentilacion>1){
                return recomendaciones[2]+" "+tiempoVentilacion+" horas";
            }else{
                return recomendaciones[2]+" "+tiempoVentilacion+" hora";
            }
        } else if (valor < 300) {
            if (tiempoVentilacion>1){
                return recomendaciones[3]+" "+tiempoVentilacion+" horas";
            }else{
                return recomendaciones[3]+" "+tiempoVentilacion+" hora";
            }
        } else {
            return recomendaciones[4];
        }
    }
}
