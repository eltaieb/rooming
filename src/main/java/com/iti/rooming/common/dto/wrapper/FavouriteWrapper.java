package com.iti.rooming.common.dto.wrapper;

import java.io.Serializable;

public class FavouriteWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long facilityId;
	private Long seekerId;

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public Long getSeekerId() {
		return seekerId;
	}

	public void setSeekerId(Long seekerId) {
		this.seekerId = seekerId;
	}

}
