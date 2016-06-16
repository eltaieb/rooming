package com.iti.rooming.common.dto.wrapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ChatMessageWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ownerFirstName;
	private List message;

	public ChatMessageWrapper() {

	}

	public List getMessage() {
		return message;
	}

	public void setMessage(List message) {
		this.message = message;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

}
