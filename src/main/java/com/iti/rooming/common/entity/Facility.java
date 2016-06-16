package com.iti.rooming.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Transient;

/**
 * The persistent class for the facility database table.
 * 
 */
@Entity
@NamedQuery(name = "Facility.findAll", query = "SELECT f FROM Facility f")
public class Facility extends BaseEntity implements Serializable,
		Comparable<Facility> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "building_number")
	private Integer buildingNumber;

	private String city;

	private String country;

	private String description;

	private Double lan;

	private Double lon;

	@Column(name = "postal_code")
	private Integer postalCode;

	private String street;
	private String gender="m";
	@Transient
	private List<FacilityImage> images = new ArrayList<FacilityImage>();

	@Transient
	private List<Room> rooms = new ArrayList<Room>();

	// bi-directional many-to-one association to RoomAdvertiser
	@ManyToOne
	@JoinColumn(name = "room_advertisor_id", referencedColumnName = "id")
	private RoomAdvertiser roomAdvertiser;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "facility_amenities", joinColumns = { @JoinColumn(name = "facility_id") }, inverseJoinColumns = { @JoinColumn(name = "amenities_id") })
	private List<Amenity> amenities;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "facility_roles", joinColumns = { @JoinColumn(name = "facility_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private List<Role> roles;

	@Transient
	private Long matches = 0L;

	public Facility() {

	}

	public Facility(Long id) {
		this.id = id;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<FacilityImage> getImages() {
		return images;
	}

	public void setImages(List<FacilityImage> images) {
		this.images = images;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBuildingNumber() {
		return this.buildingNumber;
	}

	public void setBuildingNumber(Integer buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getLan() {
		return this.lan;
	}

	public void setLan(Double lan) {
		this.lan = lan;
	}

	public Double getLon() {
		return this.lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Integer getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public RoomAdvertiser getRoomAdvertiser() {
		return this.roomAdvertiser;
	}

	public void setRoomAdvertiser(RoomAdvertiser roomAdvertiser) {
		this.roomAdvertiser = roomAdvertiser;
	}

	@Override
	public String toString() {
		return "Facility [id=" + id + ", buildingNumber=" + buildingNumber
				+ ", city=" + city + ", country=" + country + ", description="
				+ description + ", lan=" + lan + ", lon=" + lon
				+ ", postalCode=" + postalCode + ", street=" + street + "]";
	}

	public Long getMatches() {
		return matches;
	}

	public void setMatches(Long matches) {
		this.matches = matches;
	}

	public List<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<Amenity> amenities) {
		this.amenities = amenities;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int compareTo(Facility o) {
		return matches.compareTo(o.getMatches());
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}