package com.example.elastic.dto;

import com.example.elastic.model.Accident;

public class AccidentDTO {

	private String id;

	private String weatherCondition;

	private String street;

	private String startTime;

	private String location;

	private String county;

	private String city;
	

	public AccidentDTO(Accident anAccident) {
		this.setId(anAccident.getId());
		this.setWeatherCondition(anAccident.getWeatherCondition());
		this.setStreet(anAccident.getStreet());
		this.setStartTime(anAccident.getStartTime());
		this.setLocation(anAccident.getLocation().toString());
		this.setCounty(anAccident.getCounty());
		this.setCity(anAccident.getCity());
	}

	public String getId() {
		return this.id;
	}

	public void setId(String anId) {
		this.id = anId;
	}

	public String getWeatherCondition() { return weatherCondition; }

	public void setWeatherCondition(String weatherCondition) { this.weatherCondition = weatherCondition; }

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String aStreet) {
		this.street = aStreet;
	}

	public String getStartTime() { return startTime; }

	public void setStartTime(String startTime) { this.startTime = startTime; }

	public String getLocation() { return location; }

	public void setLocation(String location) { this.location = location; }

	public String getCounty() { return county; }

	public void setCounty(String county) { this.county = county; }

	public String getCity() { return city; }

	public void setCity(String city) { this.city = city; }
}