package com.iti.rooming.common.dto.wrapper;

import java.io.Serializable;

public class ChatRequestWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long ownerId;
	private Long seekerId;

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getSeekerId() {
		return seekerId;
	}

	public void setSeekerId(Long seekerId) {
		this.seekerId = seekerId;
	}

}
