package com.iti.rooming.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the facility_images database table.
 * 
 */
@Entity
@Table(name = "facility_images")
@NamedQuery(name = "FacilityImage.findAll", query = "SELECT f FROM FacilityImage f")
public class FacilityImage extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String image;

	@Column(name = "is_cover")
	private Boolean cover;
	
	// bi-directional many-to-one association to Facility
	@ManyToOne
	@JoinColumn(name = "facility_id", referencedColumnName = "id")
	private Facility facility;

	public FacilityImage() {
	}

	public Boolean getCover() {
		return cover;
	}

	public void setCover(Boolean cover) {
		this.cover = cover;
	}



	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Facility getFacility() {
		return this.facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

}