package com.iti.rooming.ws.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.dto.RoomingRequest;
import com.iti.rooming.common.dto.RoomingResponse;
import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.exception.RoomingException;

@Path("/amenity")
public class AmenityWS {

	@EJB
	private RoomingManagment roomingManagment;

	@POST
	@Path("/get/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse getAllAmenities(RoomingRequest roomingRequest)
			throws RoomingException {
		RoomingResponse roomingResponse = new RoomingResponse();
		List<Amenity> amenities = roomingManagment.getAllAmenity();
		return RoomingResponse.prepareSuccessResponse(amenities);
	}
}
