package com.iti.rooming.common.utils;

public class Bounds {
	private Double minLon;
	private Double maxLon;
	private Double minLat;
	private Double maxLat;

	public Bounds(Double minLon, Double maxLon, Double minLat, Double maxLat) {
		this.minLon = minLon;
		this.maxLon = maxLon;
		this.minLat = minLat;
		this.maxLat = maxLat;
	}

	@Override
	public String toString() {
		return "Bounds [minLon=" + minLon + ", maxLon=" + maxLon + ", minLat="
				+ minLat + ", maxLat=" + maxLat + "]";
	}

	public Double getMinLon() {
		return minLon;
	}

	public void setMinLon(Double minLon) {
		this.minLon = minLon;
	}

	public Double getMaxLon() {
		return maxLon;
	}

	public void setMaxLon(Double maxLon) {
		this.maxLon = maxLon;
	}

	public Double getMinLat() {
		return minLat;
	}

	public void setMinLat(Double minLat) {
		this.minLat = minLat;
	}

	public Double getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(Double maxLat) {
		this.maxLat = maxLat;
	}

}
