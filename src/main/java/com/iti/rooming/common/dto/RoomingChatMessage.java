package com.iti.rooming.common.dto;

import java.io.Serializable;
import java.util.Date;

public class RoomingChatMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long from;
	private Long to;
	private Long about;
	private String message;
	private Date date;
	private Long type;

	public Long getFrom() {
		return from;
	}

	public void setFrom(Long from) {
		this.from = from;
	}

	public Long getTo() {
		return to;
	}

	public void setTo(Long to) {
		this.to = to;
	}

	public Long getAbout() {
		return about;
	}

	public void setAbout(Long about) {
		this.about = about;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

}
