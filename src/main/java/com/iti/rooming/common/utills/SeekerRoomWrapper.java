package com.iti.rooming.common.utills;

import java.io.Serializable;
import java.util.List;

import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Room;
import com.iti.rooming.common.entity.RoomSeeker;

public class SeekerRoomWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private RoomSeeker roomSeeker;
	private Facility facility;

	private String identifier;

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

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {

		identifier = roomSeeker.getId() + "-" + facility.getId();
		return identifier;
	}

}
