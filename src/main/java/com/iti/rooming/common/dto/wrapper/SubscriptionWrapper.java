package com.iti.rooming.common.dto.wrapper;

public class SubscriptionWrapper {
	private Long roomSeekerID;
	private Long facilityID;

	public SubscriptionWrapper(Long rookSeekerID, Long facilityID) {
		this.roomSeekerID = rookSeekerID;
		this.facilityID = facilityID;
	}

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
