package com.iti.rooming.common.utils;

public class GeoLocation {
	private final double EARTH_RADIUS = 6371.01;
	private Double lat, lon, deltaLon;
	private double r;

	public GeoLocation(Double lon, Double lat, Double area) {
		this.lon = getRadians(lon);
		this.lat = getRadians(lat);
		calcRadius(area);
		calcDeltaLon();
	}

	private Double getRadians(Double degree) {
		return (degree * Math.PI) / 180;
	}

	private void calcRadius(Double meters) {
		r = (meters / 1000) / EARTH_RADIUS;
	}

	private void calcDeltaLon() {
		deltaLon = Math.asin(Math.sin(r) / Math.cos(lat));
	}

	public Bounds getBounds() {
		return new Bounds(getMinLon(), getMaxLon(), getMinLat(), getMaxLat());
	}

	private Double getMinLon() {
		return lon - deltaLon;
	}

	private Double getMaxLon() {
		return lon + deltaLon;
	}

	private Double getMaxLat() {
		return lat + r;
	}

	private Double getMinLat() {
		return lat - r;
	}

}
