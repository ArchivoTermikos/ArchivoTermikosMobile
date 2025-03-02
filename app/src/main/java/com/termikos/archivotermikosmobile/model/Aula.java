package com.termikos.archivotermikosmobile.model;


import java.util.List;

public class Aula {
    private String nombre;
    private List<AulaEntry> entries;

    public Aula() {
    }

    public Aula(String nombre, List<AulaEntry> entries) {
        this.nombre = nombre;
        this.entries = entries;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<AulaEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<AulaEntry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "nombre='" + nombre + '\'' +
                ", entries=" + entries +
                '}';
    }
}
