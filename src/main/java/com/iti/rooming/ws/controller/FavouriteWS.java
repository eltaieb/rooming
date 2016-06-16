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
import com.iti.rooming.common.dto.wrapper.FacilityWrapper;
import com.iti.rooming.common.dto.wrapper.FavouriteWrapper;
import com.iti.rooming.common.dto.wrapper.RoomWrapper;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.JsonUtil;

@Path("/favourite")
public class FavouriteWS {
	@EJB
	private RoomingManagment roomingManagement;
	private Gson gson = new Gson();

	@POST
	@Path("/get/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse getFavourit(RoomingRequest roomingRequest) {
		String json = gson.toJson(roomingRequest.getObject());
		Long seekerId = gson.fromJson(json, Long.class);
		List<FacilityWrapper> facilityWrappers = roomingManagement
				.getAllFavourite(seekerId);
		return RoomingResponse.prepareSuccessResponse(facilityWrappers);

	}

	@POST
	@Path("/add/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse addFavourite(RoomingRequest roomingRequest)
			throws RoomingException {

		String json = gson.toJson(roomingRequest.getObject());
		System.out.println(json);

		FavouriteWrapper favouriteWrapper = gson.fromJson(json,
				FavouriteWrapper.class);

		roomingManagement.addFavouriteFacility(favouriteWrapper);
		return RoomingResponse.prepareSuccessResponse(null);
	}

	@POST
	@Path("/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse deleteFavourite(RoomingRequest roomingRequest)
			throws RoomingException {
		String json = gson.toJson(roomingRequest.getObject());

		FavouriteWrapper favouriteWrapper = gson.fromJson(json,
				FavouriteWrapper.class);

		roomingManagement.removeFavouriteFacility(favouriteWrapper);
		return RoomingResponse.prepareSuccessResponse(null);

	}
}
