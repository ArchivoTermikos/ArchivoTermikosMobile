package com.termikos.archivotermikosmobile.model;

public class ClaseCard extends ElementoTarjeta {

    private int imageResId;

    public ClaseCard(String title, String subtitle, int current, int imageResId) {
        super(title, subtitle, current);
        this.imageResId = imageResId;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
