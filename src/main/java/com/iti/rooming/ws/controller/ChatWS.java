package com.iti.rooming.ws.controller;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.dto.RoomingRequest;
import com.iti.rooming.common.dto.RoomingResponse;
import com.iti.rooming.common.dto.wrapper.ChatRequestWrapper;
import com.iti.rooming.common.dto.wrapper.OwnerWrapper;
import com.iti.rooming.common.dto.wrapper.SeekerWrapper;
import com.iti.rooming.common.utils.JsonUtil;

@Path("/chat")
public class ChatWS {

	@EJB
	private RoomingManagment roomingManagement;

	@POST
	@Path("/get/owners")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse getOwners(RoomingRequest request) {
		String json = JsonUtil.getJson(request.getObject());
		Long seekerId = JsonUtil.jsonToObject(json, Long.class);
		List<OwnerWrapper> owners = roomingManagement
				.getChatOwnersOfSeeker(seekerId);
		if (owners == null)
			return RoomingResponse.prepareFailedResponse(null);
		return RoomingResponse.prepareSuccessResponse(owners
				.toArray(new OwnerWrapper[] {}));
	}

	@POST
	@Path("/get/messages")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse getMessages(RoomingRequest request) {
		String json = JsonUtil.getJson(request.getObject());
		ChatRequestWrapper chatRequestWrapper = JsonUtil.jsonToObject(json,
				ChatRequestWrapper.class);
		List messages = roomingManagement.getAllMessagesTo(chatRequestWrapper);
		if (messages == null)
			return RoomingResponse.prepareFailedResponse(null);
		RoomingResponse response = new RoomingResponse();
		response.setCode("1111");
		response.setObject(messages);
		response.setMessage("success");
		return response;
	}


}
