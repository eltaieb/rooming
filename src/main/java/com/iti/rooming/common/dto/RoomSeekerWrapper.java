package com.iti.rooming.common.dto;

import java.io.Serializable;

public class RoomSeekerWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long roomSeekerID;
	private Long facilityID;

	public Long getRoomSeekerID() {
		return roomSeekerID;
	}

	public void setRoomSeekerID(Long roomSeekerID) {
		this.roomSeekerID = roomSeekerID;
	}

	public Long getFacilityID() {
		return facilityID;
	}

	public void setFacilityID(Long facilityID) {
		this.facilityID = facilityID;
	}

}
