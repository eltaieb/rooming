package com.iti.rooming.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Subselect;

//com.iti.rooming.common.entity.SelectionView;

@Entity
@Subselect("select * from selection_view")
public class SelectionView implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "facility_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Facility facility;

	@Column(name = "facility_id")
	private Long facilityId;
	@Transient
	private Long onwerId;
	private Double longitude;
	private Double latitude;
	@Column(name = "price")
	private Double price;
	@Column(name = "roomid")
	private Long roomId;
	@Transient
	private Long numberOfRooms;
	private String street;
	private String city;
	private String address;
	@Column(name = "building_number")
	private Long buildingNumber;
	@Column(name = "profile_image")
	private String profileImage;
	@Column(name = "facility_cover_photo")
	private String facilityCoverPhoto;
	private String description;
	@Column(name = "bookmark_flag")
	private Long bookmark = 0L;
	@Column(name = "subscription_flag")
	private Long subscription = 0L;

	public SelectionView() {
	}

	public SelectionView(Facility facility, Double longitude, Double latitude,
			Double minPrice, Long numberOfRooms, String profileImage,
			String facilityCoverPhoto, String description, Long bookmark,
			Long subscription) {
		super();
		this.facility = facility;
		setOnwerId(facility.getRoomAdvertiser().getId());
		this.longitude = longitude;
		this.latitude = latitude;
		this.price = minPrice;
		this.numberOfRooms = numberOfRooms;
		this.profileImage = profileImage;
		this.facilityCoverPhoto = facilityCoverPhoto;
		this.description = description;
		setBookmark(bookmark);
		setSubscription(subscription);
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(Long buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getFacilityCoverPhoto() {
		return facilityCoverPhoto;
	}

	public void setFacilityCoverPhoto(String facilityCoverPhoto) {
		this.facilityCoverPhoto = facilityCoverPhoto;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		return facilityId.equals(obj);
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Long numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public Long getBookmark() {
		if (this.bookmark != null)
			return bookmark;
		else
			return 0L;
	}

	public void setBookmark(Long bookmark) {
		if (bookmark != null)
			this.bookmark = bookmark;
		else
			this.bookmark = 0L;
	}

	public Long getSubscription() {
		if (this.subscription != null)
			return subscription;
		else
			return 0L;
	}

	public void setSubscription(Long subscription) {
		if (subscription != null)
			this.subscription = subscription;
		else
			this.subscription = 0L;
	}

	public Long getOnwerId() {
		return onwerId;
	}

	public void setOnwerId(Long onwerId) {
		this.onwerId = onwerId;
	}

}
