package com.iti.rooming.ws.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.dto.RoomingRequest;
import com.iti.rooming.common.dto.RoomingResponse;
import com.iti.rooming.common.dto.wrapper.RoomWrapper;
import com.iti.rooming.common.utils.JsonUtil;

@Path("/room")
public class RoomWS {

	@EJB
	private RoomingManagment roomingManagment;

	@POST
	@Path("/get")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse getAllRooms(RoomingRequest request) {
		String json = JsonUtil.getJson(request.getObject());
		Long facilityID = JsonUtil.jsonToObject(json, Long.class);
		List<RoomWrapper> result = roomingManagment
				.getAllRoomOfFacility(facilityID);
		return RoomingResponse.prepareSuccessResponse(result);

	}
}
