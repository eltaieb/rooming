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
	
	
}