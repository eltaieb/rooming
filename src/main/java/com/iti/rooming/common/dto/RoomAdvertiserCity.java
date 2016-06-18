package com.iti.rooming.common.dto;

import java.io.Serializable;

public class RoomAdvertiserCity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long nOfRoomAdvertisr;
	private String city;

	public RoomAdvertiserCity(Long nOfRoomAdvertisr, String city) {
		super();
		this.nOfRoomAdvertisr = nOfRoomAdvertisr;
		this.city = city;
	}

	public Long getnOfRoomAdvertisr() {
		return nOfRoomAdvertisr;
	}

	public void setnOfRoomAdvertisr(Long nOfRoomAdvertisr) {
		this.nOfRoomAdvertisr = nOfRoomAdvertisr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
