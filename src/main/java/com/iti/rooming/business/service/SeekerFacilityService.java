package com.iti.rooming.business.service;

import javax.ejb.Local;


import com.iti.rooming.common.dto.RoomSeekerWrapper;
import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.exception.RoomingException;

@Local
public interface SeekerFacilityService  {
	public void subscribeSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException;

	public void unsubscribeSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException;

	public void updateSubscriptionSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException;
}
