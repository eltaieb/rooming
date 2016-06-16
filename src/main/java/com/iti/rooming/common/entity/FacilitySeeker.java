package com.iti.rooming.common.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "bookmark")
@NamedQuery(name = "FacilitySeeker.findAll", query = "SELECT f FROM FacilitySeeker f")
public class FacilitySeeker implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "facility_id", referencedColumnName = "id")
	private Facility facility;
	@ManyToOne
	@JoinColumn(name = "seeker_id", referencedColumnName = "id")
	private RoomSeeker roomSeeker;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public RoomSeeker getRoomSeeker() {
		return roomSeeker;
	}

	public void setRoomSeeker(RoomSeeker roomSeeker) {
		this.roomSeeker = roomSeeker;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
