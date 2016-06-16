package com.iti.rooming.common.exception;

public class RoomingException extends Exception {
	private static final long serialVersionUID = 1L;

	private String messageKey;

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public RoomingException(String messageKey) {
		super();
		this.messageKey = messageKey;
	}

}
