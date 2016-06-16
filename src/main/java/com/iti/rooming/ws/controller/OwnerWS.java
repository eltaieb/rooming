package com.iti.rooming.ws.controller;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.dto.RoomingRequest;
import com.iti.rooming.common.dto.RoomingResponse;
import com.iti.rooming.common.dto.wrapper.OwnerWrapper;
import com.iti.rooming.common.utils.JsonUtil;

@Path("/owner")
public class OwnerWS {

	@EJB
	private RoomingManagment roomingManagement;

	@POST
	@Path("/get/profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse getOwnerProfile(RoomingRequest request) {
		String json = JsonUtil.getJson(request.getObject());
		Long ownerId = JsonUtil.jsonToObject(json, Long.class);
		OwnerWrapper ownerWrapper = roomingManagement.getOwnerProfile(ownerId);
		if (ownerWrapper != null)
			return RoomingResponse.prepareSuccessResponse(ownerWrapper);
		return RoomingResponse.prepareFailedResponse(null); 
	}
}
