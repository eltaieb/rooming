package com.iti.rooming.common.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

import javax.persistence.ManyToOne;

/**
 * The persistent class for the room_advertisers database table.
 * 
 */
@Entity
@Table(name = "room_advertisers")
@NamedQuery(name = "RoomAdvertiser.findAll", query = "SELECT r FROM RoomAdvertiser r")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class RoomAdvertiser extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "building_number")
	private Integer buildingNumber;

	private String city;

	private String country;

	@Column(name = "floor_number")
	private Integer floorNumber;

	@Column(name = "phone_number")
	private Integer phoneNumber;

	@Column(name = "profile_image")
	private String profileImage;

	private String street;

	@Column(name = "identification_number")
	private String identificationNumber;

	@Column(name = "identification_document_path")
	private String identificationDocumentPath;

	@ManyToOne
	@JoinColumn(name = "identification_type", referencedColumnName = "id")
	private Lookup identificationType;

	@Temporal(TemporalType.DATE)
	private Date birthday;
	@Column(name = "is_verified")
	private Boolean isVerified;

	// Lookup IdentificationType

	// String identification_document_Path

	public Lookup getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(Lookup identificationType) {
		this.identificationType = identificationType;
	}

	public RoomAdvertiser() {
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

	public Integer getFloorNumber() {
		return this.floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	public Integer getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProfileImage() {
		return this.profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getIdentificationDocumentPath() {
		return identificationDocumentPath;
	}

	public void setIdentificationDocumentPath(String identificationDocumentPath) {
		this.identificationDocumentPath = identificationDocumentPath;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

}