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
 * The persistent class for the facility_amenities database table.
 * 
 */
@Entity
@Table(name="facility_amenities")
@NamedQuery(name="FacilityAmenity.findAll", query="SELECT f FROM FacilityAmenity f")
public class FacilityAmenity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	//bi-directional many-to-one association to Amenity
	@ManyToOne
	@JoinColumn(name="amenities_id", referencedColumnName="id")
	private Amenity amenity;

	//bi-directional many-to-one association to Facility
	@ManyToOne
	@JoinColumn(name="facility_id", referencedColumnName="id")
	private Facility facility;

	public FacilityAmenity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Amenity getAmenity() {
		return this.amenity;
	}

	public void setAmenity(Amenity amenity) {
		this.amenity = amenity;
	}

	public Facility getFacility() {
		return this.facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

}