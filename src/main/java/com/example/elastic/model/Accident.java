package com.example.elastic.model;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.geo.Point;

import java.util.ArrayList;

@Document(indexName = "accidentes")
public class Accident {

	@Id
	private String id;

	@Field(type= FieldType.Keyword, name="Weather_Condition")
	private String weatherCondition;

	@Field(type= FieldType.Keyword, name="Street")
	private String street;

	@Field(type= FieldType.Keyword, name="@timestamp")
	private String startTime;

	@Field("start_location")
	private Point location;

	@Field(type= FieldType.Keyword, name="County")
	private String county;

	@Field(type= FieldType.Keyword, name="City")
	private String city;



	public Accident(@Value("Weather_Condition") String aWeatherCondition, @Value("Street") String aStreet, @Value("@timestamp") String aStartTime, @Value("start_location") Point aLocation, @Value("County") String aCounty, @Value("City") String aCity) {
		this.setWeatherCondition(aWeatherCondition);
		this.setStreet(aStreet);
		this.setStartTime(aStartTime);
		this.setLocation(aLocation);
		this.setCounty(aCounty);
		this.setCity(aCity);
	}
	public Accident() {
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

	public Point getLocation() { return location; }

	public void setLocation(Point location) { this.location = location; }

	public String getCounty() { return county; }

	public void setCounty(String county) { this.county = county; }

	public String getCity() { return city; }

	public void setCity(String city) { this.city = city; }
}
