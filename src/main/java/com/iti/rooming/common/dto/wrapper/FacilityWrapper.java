package com.iti.rooming.common.dto.wrapper;

import java.io.Serializable;
import java.util.List;

public class FacilityWrapper implements Serializable,
		Comparable<FacilityWrapper> {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long ownerId;
	private String facilityCover;
	private String profileImage;
	private Double minPrice;
	private Double lon;
	private Double lat;
	private String description;
	private Boolean isFavourite;
	private Boolean isSubscribe;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFacilityCover() {
		return facilityCover;
	}

	public void setFacilityCover(String facilityCover) {
		this.facilityCover = facilityCover;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(FacilityWrapper o) {
		return id.compareTo(o.getId());
	}

	public Boolean getIsFavourite() {
		return isFavourite;
	}

	public void setIsFavourite(Boolean isFavourite) {
		this.isFavourite = isFavourite;
	}

	public Boolean getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(Boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

}
