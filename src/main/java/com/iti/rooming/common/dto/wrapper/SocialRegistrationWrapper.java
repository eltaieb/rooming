package com.iti.rooming.common.dto.wrapper;

import java.io.Serializable;

public class SocialRegistrationWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private String email;
	private String fName;
	private String lName;
	private String gender;
	private Boolean isFacebook;
	private String token;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Boolean getIsFacebook() {
		return isFacebook;
	}
	public void setIsFacebook(Boolean isFacebook) {
		this.isFacebook = isFacebook;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	

}
