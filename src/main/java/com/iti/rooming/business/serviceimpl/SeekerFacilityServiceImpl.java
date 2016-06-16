package com.iti.rooming.business.serviceimpl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.iti.rooming.business.service.SeekerFacilityService;
import com.iti.rooming.common.dto.RoomSeekerWrapper;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.dataaccess.dao.SeekerFacilityDAO;

@Stateless
public class SeekerFacilityServiceImpl 
		implements SeekerFacilityService {
	@EJB
	private SeekerFacilityDAO seekerFacilityDAO;

	@Override
	public void subscribeSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException {
		seekerFacilityDAO.subscribeSeeker(roomSeekerWrapper);
	}

	@Override
	public void unsubscribeSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException {
		seekerFacilityDAO.unsubscribeSeeker(roomSeekerWrapper);
	}

	@Override
	public void updateSubscriptionSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException {
		seekerFacilityDAO.updateSubscriptionSeeker(roomSeekerWrapper);

	}
}
