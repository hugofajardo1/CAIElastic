package com.example.elastic.model;

public class Punto {

    private String id;

    private Float latitud;

    private Float longitud;

    private Integer cont;

    public Punto(String id, Integer cont) {
        this.setId(id);
        int posicion = id.indexOf(",");
        this.setLatitud(Float.parseFloat(id.substring(0, posicion-1)));
        this.setLongitud(Float.parseFloat(id.substring(posicion+1, id.length()-1)));
        this.setCont(cont);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public Integer getCont() {
        return cont;
    }

    public void setCont(Integer cont) {
        this.cont = cont;
    }
}
