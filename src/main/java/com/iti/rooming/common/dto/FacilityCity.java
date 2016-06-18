package com.iti.rooming.common.dto;

import java.io.Serializable;

public class FacilityCity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long nOfFacilities;
	private String city;

	public FacilityCity(Long nOfFacilities, String city) {
		this.nOfFacilities = nOfFacilities;
		this.city = city;
	}

	public Long getnOfFacilities() {
		return nOfFacilities;
	}

	public void setnOfFacilities(Long nOfFacilities) {
		this.nOfFacilities = nOfFacilities;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
