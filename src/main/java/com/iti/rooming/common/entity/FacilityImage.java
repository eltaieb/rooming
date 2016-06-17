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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cover == null) ? 0 : cover.hashCode());
		result = prime * result
				+ ((facility == null) ? 0 : facility.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FacilityImage other = (FacilityImage) obj;
		if (cover == null) {
			if (other.cover != null)
				return false;
		} else if (!cover.equals(other.cover))
			return false;
		if (facility == null) {
			if (other.facility != null)
				return false;
		} else if (!facility.equals(other.facility))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		return true;
	}
	
	

}