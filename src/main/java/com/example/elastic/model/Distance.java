package com.example.elastic.model;

public class Distance {

    private String id;

    private String distance;

    public Distance(String id, String distance) {
        this.setId(id);
        this.setDistance(distance);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistance() { return distance; }

    public void setDistance(String distance) { this.distance = distance; }
}
