package com.termikos.archivotermikosmobile.model;

public class MiembroCard extends ElementoTarjeta{
    private int lastImage;

    public MiembroCard(String title, String subtitle, int current, int lastImage) {
        super(title, subtitle, current);
        this.lastImage = lastImage;
    }

    public int change(){
        int temp = current;
        current = lastImage;
        lastImage = temp;
        return current;
    }

    public int getLast() {
        return lastImage;
    }
}
