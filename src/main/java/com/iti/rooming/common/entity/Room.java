package com.iti.rooming.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the room database table.
 * 
 */
@Entity
@NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r")
public class Room extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "advertise_date")
	private Date advertiseDate;

	private String area;

	private Integer duration;

	private Boolean expandable;

	@Column(name = "rent_available")
	private Boolean rentAvailable = true;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	private Double price;

	// bi-directional many-to-one association to Facility
	@ManyToOne
	@JoinColumn(name = "facility_id", referencedColumnName = "id")
	private Facility facility;
	
	@Transient
	private List<RoomImage> images= new ArrayList<RoomImage>() ;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy= "room")
	private List<RoomImage> roomImages;	
	
	public Boolean getRentAvailable() {
		return rentAvailable;
	}

	public void setRentAvailable(Boolean rentAvailable) {
		this.rentAvailable = rentAvailable;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<RoomImage> getImages() {
		return images;
	}

	public void setImages(List<RoomImage> image) {
		this.images = image;
	}

	public Room() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAdvertiseDate() {
		return this.advertiseDate;
	}

	public void setAdvertiseDate(Date advertiseDate) {
		this.advertiseDate = advertiseDate;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}


	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Facility getFacility() {
		return this.facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public Boolean getExpandable() {
		return expandable;
	}

	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", advertiseDate=" + advertiseDate
				+ ", area=" + area + ", duration=" + duration + ", expandable="
				+ expandable + ", price=" + price + ", facility=" + facility
				+ "]";
	}

	public List<RoomImage> getRoomImages() {
		return roomImages;
	}

	public void setRoomImages(List<RoomImage> roomImages) {
		this.roomImages = roomImages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((advertiseDate == null) ? 0 : advertiseDate.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result
				+ ((duration == null) ? 0 : duration.hashCode());
		result = prime * result
				+ ((expandable == null) ? 0 : expandable.hashCode());
		result = prime * result
				+ ((facility == null) ? 0 : facility.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result
				+ ((isDeleted == null) ? 0 : isDeleted.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((rentAvailable == null) ? 0 : rentAvailable.hashCode());
		result = prime * result
				+ ((roomImages == null) ? 0 : roomImages.hashCode());
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
		Room other = (Room) obj;
		if (advertiseDate == null) {
			if (other.advertiseDate != null)
				return false;
		} else if (!advertiseDate.equals(other.advertiseDate))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (expandable == null) {
			if (other.expandable != null)
				return false;
		} else if (!expandable.equals(other.expandable))
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
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (isDeleted == null) {
			if (other.isDeleted != null)
				return false;
		} else if (!isDeleted.equals(other.isDeleted))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (rentAvailable == null) {
			if (other.rentAvailable != null)
				return false;
		} else if (!rentAvailable.equals(other.rentAvailable))
			return false;
		if (roomImages == null) {
			if (other.roomImages != null)
				return false;
		} else if (!roomImages.equals(other.roomImages))
			return false;
		return true;
	}
	
	
	
}