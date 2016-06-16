package com.iti.rooming.ws.applicationcontext;

import java.security.acl.Owner;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.iti.rooming.common.dto.wrapper.OwnerWrapper;
import com.iti.rooming.ws.controller.AmenityWS;
import com.iti.rooming.ws.controller.ChatWS;
import com.iti.rooming.ws.controller.FacilityWS;
import com.iti.rooming.ws.controller.FavouriteWS;
import com.iti.rooming.ws.controller.OwnerWS;
import com.iti.rooming.ws.controller.RoleWS;
import com.iti.rooming.ws.controller.RoomWS;
import com.iti.rooming.ws.controller.SeekerWs;

public class MessageApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set services = new HashSet<>();
		services.add(FacilityWS.class);
		services.add(AmenityWS.class);
		services.add(RoleWS.class);
		services.add(RoomWS.class);
		services.add(FavouriteWS.class);
		services.add(OwnerWS.class);
		services.add(SeekerWs.class);
		services.add(ChatWS.class);
		return services;
	}

}
