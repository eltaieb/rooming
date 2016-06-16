package com.iti.rooming.common.utils;

import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.RoomSeeker;

public class SeekerFacilityWrapper {

	private RoomSeeker roomSeeker;
	private Facility facility;

	public RoomSeeker getRoomSeeker() {
		return roomSeeker;
	}

	public void setRoomSeeker(RoomSeeker roomSeeker) {
		this.roomSeeker = roomSeeker;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

}
