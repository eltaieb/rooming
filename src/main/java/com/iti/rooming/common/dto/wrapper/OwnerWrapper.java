package com.iti.rooming.common.dto.wrapper;

import java.io.Serializable;

public class OwnerWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String fName;
	private String lName;
	private Boolean isVerified;
	private String image;

	public OwnerWrapper() {

	}

	public OwnerWrapper(Long id, String fName, String lName,
			Boolean isVerified, String image) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.isVerified = isVerified;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
