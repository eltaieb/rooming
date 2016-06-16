package com.iti.rooming.dataaccess.dao;

import javax.ejb.Local;

import com.iti.rooming.common.dto.RoomSeekerWrapper;
import com.iti.rooming.common.exception.RoomingException;

@Local
public interface SeekerFacilityDAO {
	public void subscribeSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException;

	public void unsubscribeSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException;

	public void updateSubscriptionSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException;
}
