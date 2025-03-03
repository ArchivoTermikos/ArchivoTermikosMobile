package com.termikos.archivotermikosmobile.model;

public class ElementoTarjeta {
    protected String title;
    protected String subtitle;
    protected int current;

    public ElementoTarjeta(String title, String subtitle, int current) {
        this.title = title;
        this.subtitle = subtitle;
        this.current = current;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getCurrent() {
        return current;
    }

}
