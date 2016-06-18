package com.iti.rooming.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FacilitySelectionCriteria implements Serializable {
	private static final long serialVersionUID = 1L;
	private Double lon;
	private Double lat;
	private Double area;
	private Double minPrice;
	private Double maxPrice;
	private String gender;
	private List<Long> roles = new ArrayList();
	private List<Long> amenities = new ArrayList();

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public List<Long> getRoles() {
		return roles;
	}

	public void setRoles(List<Long> roles) {
		this.roles = roles;
	}

	public List<Long> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<Long> amenities) {
		this.amenities = amenities;
	}

}
