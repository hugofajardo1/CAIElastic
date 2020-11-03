package com.example.elastic.dto;

import com.example.elastic.model.Distance;

public class DistanceDTO {

    private String id;

    private String distance;

    public DistanceDTO(Distance aDistance) {
        this.setId(aDistance.getId());
        this.setDistance(aDistance.getDistance());
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistance() { return distance; }

    public void setDistance(String distance) { this.distance = distance; }
}
