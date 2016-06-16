package com.iti.rooming.common.dto.wrapper;

import java.io.Serializable;

import com.iti.rooming.common.utils.Bounds;

public class FacilitySelectionCriteriaWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Bounds bounds;
	private RoomPriceWrapper roomPriceWrapper;

	public Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	public RoomPriceWrapper getRoomPriceWrapper() {
		return roomPriceWrapper;
	}

	public void setRoomPriceWrapper(RoomPriceWrapper roomPriceWrapper) {
		this.roomPriceWrapper = roomPriceWrapper;
	}

}
