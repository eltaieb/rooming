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

/**
 * The persistent class for the searcher_interests database table.
 * 
 */
@Entity
@Table(name = "seeker_favourite_facility")
@NamedQuery(name = "SeekerFavouriteFacility.findAll", query = "SELECT s FROM SeekerFavouriteFacility s")
public class SeekerFavouriteFacility extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// bi-directional many-to-one association to RoomSeeker
	@ManyToOne
	@JoinColumn(name = "seeker_id", referencedColumnName = "id")
	private RoomSeeker seeker;

	// bi-directional many-to-one association to Lookup
	@ManyToOne
	@JoinColumn(name = "facility_id", referencedColumnName = "id")
	private Facility facility;

	public SeekerFavouriteFacility() {
	}

	public SeekerFavouriteFacility(RoomSeeker seeker, Facility facility) {
		super();
		this.seeker = seeker;
		this.facility = facility;
	}

	public SeekerFavouriteFacility(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoomSeeker getSeeker() {
		return seeker;
	}

	public void setSeeker(RoomSeeker seeker) {
		this.seeker = seeker;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

}