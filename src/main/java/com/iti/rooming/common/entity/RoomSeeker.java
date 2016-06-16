package com.iti.rooming.common.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the room_seekers database table.
 * 
 */
@Entity
@Table(name = "room_seekers")
@NamedQuery(name = "RoomSeeker.findAll", query = "SELECT r FROM RoomSeeker r")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class RoomSeeker extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String country;
	private String city;
	private String address;
	@Column(name = "mobile_number")
	private Integer mobileNumber;
	@Column(name = "phone_number")
	private Integer phoneNumber;
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	@Column(name = "profile_image")
	private String profileImage;

	public RoomSeeker() {
	}

	public RoomSeeker(Long id) {
		setId(id);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public Integer getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}