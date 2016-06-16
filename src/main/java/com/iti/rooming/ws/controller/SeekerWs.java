package com.iti.rooming.ws.controller;

import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.config.Configurations;
import com.iti.rooming.common.dto.RoomingRequest;
import com.iti.rooming.common.dto.RoomingResponse;
import com.iti.rooming.common.dto.wrapper.LoginWrapper;
import com.iti.rooming.common.dto.wrapper.RegistrationWrapper;
import com.iti.rooming.common.dto.wrapper.ResetPasswordWrapper;
import com.iti.rooming.common.dto.wrapper.RoomWrapper;
import com.iti.rooming.common.dto.wrapper.SeekerIdentityWrapper;
import com.iti.rooming.common.dto.wrapper.SeekerWrapper;
import com.iti.rooming.common.dto.wrapper.SocialRegistrationWrapper;
import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.utils.EncryptionMD5;
import com.iti.rooming.common.utils.JsonUtil;
import com.iti.rooming.common.utils.RandomString;

@Path("/seeker")
public class SeekerWs {

	@EJB
	private RoomingManagment roomingManagment;

	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse register(RoomingRequest request) {
		String json = JsonUtil.getJson(request.getObject());
		RegistrationWrapper registrationWrapper = JsonUtil.jsonToObject(json,
				RegistrationWrapper.class);
		SeekerIdentityWrapper seekerIdentityWrapper = roomingManagment
				.registerSeeker(registrationWrapper);
		if (seekerIdentityWrapper != null)
			return RoomingResponse
					.prepareSuccessResponse(seekerIdentityWrapper);
		else
			return RoomingResponse.prepareFailedResponse(null);
	}

	@Path("/register/social")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse registerFromSocial(RoomingRequest request) {
		String json = JsonUtil.getJson(request.getObject());
		SocialRegistrationWrapper registrationWrapper = JsonUtil.jsonToObject(
				json, SocialRegistrationWrapper.class);
		RoomingResponse response = new RoomingResponse();
		SeekerIdentityWrapper seekerIdentityWrapper = roomingManagment
				.registerSeeker(registrationWrapper);
		if (seekerIdentityWrapper != null)
			return RoomingResponse
					.prepareSuccessResponse(seekerIdentityWrapper);
		else
			return RoomingResponse.prepareFailedResponse(null);
	}
	


	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse login(RoomingRequest request) {
		String json = JsonUtil.getJson(request.getObject());
		LoginWrapper loginWrapper = JsonUtil.jsonToObject(json,
				LoginWrapper.class);
		SeekerWrapper seekerWrapper = roomingManagment.loginSeeker(loginWrapper);
		if (seekerWrapper!=null)
			return RoomingResponse.prepareSuccessResponse(seekerWrapper);
		else
			return RoomingResponse.prepareFailedResponse(null);

	}

	@Path("/reset/password")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse resetPassword(RoomingRequest request) {
		String json = JsonUtil.getJson(request.getObject());
		ResetPasswordWrapper resetPasswordWrapper = JsonUtil.jsonToObject(json,
				ResetPasswordWrapper.class);
		Boolean status = roomingManagment
				.resetSeekerPassword(resetPasswordWrapper);
		if (status)
			return RoomingResponse.prepareSuccessResponse(null);
		return RoomingResponse.prepareFailedResponse(null);
	}

	@POST
	@Path("/put/profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoomingResponse updateProfile(RoomingRequest request) {
		String json = JsonUtil.getJson(request.getObject());
		SeekerWrapper seekerWrapper = JsonUtil.jsonToObject(json,
				SeekerWrapper.class);
		Boolean status = roomingManagment.updateSeekerProfile(seekerWrapper);
		if (!status)
			return RoomingResponse.prepareFailedResponse(null);
		return RoomingResponse.prepareSuccessResponse(null);
	}
}
