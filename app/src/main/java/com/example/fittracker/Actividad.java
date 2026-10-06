package com.example.fittracker;

public class Actividad {

    private String tipo;
    private String intensidad;
    private String aspectos;
    private float esfuerzo;

    public Actividad(String tipo, String intensidad, String aspectos, float esfuerzo) {
        this.tipo = tipo;
        this.intensidad = intensidad;
        this.aspectos = aspectos;
        this.esfuerzo = esfuerzo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getIntensidad() {
        return intensidad;
    }

    public String getAspectos() {
        return aspectos;
    }

    public float getEsfuerzo() {
        return esfuerzo;
    }
}