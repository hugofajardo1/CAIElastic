package com.example.elastic.dto;

import com.example.elastic.model.Punto;

public class PuntoDTO {
    private String id;

    private Float latitud;

    private Float longitud;

    private Integer cantidad;

   public PuntoDTO(Punto unPunto){
       this.setId(unPunto.getId());
       this.setLatitud(unPunto.getLatitud());
       this.setLongitud(unPunto.getLongitud());
       this.setCantidad(unPunto.getCont());
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

    public Integer getCantidad() { return cantidad; }

    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
