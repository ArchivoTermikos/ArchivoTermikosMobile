package com.termikos.archivotermikosmobile.model;

public class IntegranteEquipo {
    private String title;
    private String subtitle;
    private int current;
    private int last;

    public IntegranteEquipo(String title, String subtitle, int imageResId, int newImageResId) {
        this.title = title;
        this.subtitle = subtitle;
        this.current = imageResId;
        this.last = newImageResId;
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

    public int change(){
        int temp = current;
        current = last;
        last = temp;
        return current;
    }
    public int getLast() {
        return last;
    }
}
