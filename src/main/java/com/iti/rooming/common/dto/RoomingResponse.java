package com.iti.rooming.common.dto;

public class RoomingResponse {
	private String code;
	private String message;
	private Object object;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static RoomingResponse prepareFailedResponse(Object obj) {
		RoomingResponse response = new RoomingResponse();
		response.setCode("0000");
		response.setMessage("failed");
		response.setObject(obj);
		return response;
	}

	public static RoomingResponse prepareSuccessResponse(Object obj) {
		RoomingResponse response = new RoomingResponse();
		response.setCode("0000");
		response.setMessage("success");
		response.setObject(obj);
		return response;
	}
}