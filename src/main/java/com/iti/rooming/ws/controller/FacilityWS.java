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
import com.iti.rooming.common.dto.FacilitySelectionCriteria;
import com.iti.rooming.common.dto.RoomSeekerWrapper;
import com.iti.rooming.common.dto.RoomingRequest;
import com.iti.rooming.common.dto.RoomingResponse;
import com.iti.rooming.common.dto.wrapper.FacilityWrapper;
import com.iti.rooming.common.dto.wrapper.FavouriteWrapper;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.enums.WebServiceCode;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.Bounds;

@Path("/facility")
public class FacilityWS {
	@EJB
	private RoomingManagment roomingManagement;
	private Gson gson = new Gson();

	@POST
	@Path("/get/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse getAllFacilities(RoomingRequest roomingRequest)
			throws RoomingException {

		String json = gson.toJson(roomingRequest.getObject());
		FacilitySelectionCriteria facilitySelectionCriteria = gson.fromJson(
				json, FacilitySelectionCriteria.class);

		List<FacilityWrapper> facilities = roomingManagement
				.getAllFacilitiesWithCriteria(facilitySelectionCriteria);
		return RoomingResponse.prepareSuccessResponse(facilities
				.toArray(new FacilityWrapper[] {}));
	}

	@POST
	@Path("/subscribe/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse subscribe(RoomingRequest roomingRequest)
			throws RoomingException {

		String json = gson.toJson(roomingRequest.getObject());
		System.out.println(json);

		RoomSeekerWrapper roomSeekerWrapper = gson.fromJson(json,
				RoomSeekerWrapper.class);
		roomingManagement.subscribeSeeker(roomSeekerWrapper);
		return RoomingResponse.prepareSuccessResponse(null);
	}

	@POST
	@Path("/unsubscribe")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse unsubscribe(RoomingRequest roomingRequest)
			throws RoomingException {
		String json = gson.toJson(roomingRequest.getObject());

		RoomSeekerWrapper roomSeekerWrapper = gson.fromJson(json,
				RoomSeekerWrapper.class);

		roomingManagement.unsubscribeSeeker(roomSeekerWrapper);
		return RoomingResponse.prepareSuccessResponse(null);

	}

}
