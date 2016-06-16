package com.iti.rooming.business.serviceimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.iti.rooming.business.service.FacilityAmenityService;
import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityAmenity;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.dataaccess.dao.FacilityAmenityDAO;

@Stateless
public class FacilityAmenityServiceImpl implements FacilityAmenityService{

	@EJB
	FacilityAmenityDAO facilityAmenityDAO;

	@Override
	public FacilityAmenity addOrUpdate(FacilityAmenity value) throws RoomingException {
		// TODO Auto-generated method stub
		return facilityAmenityDAO.addOrUpdate(value);
	}

	@Override
	public List<Amenity> getAmenitiesOfFacility(Facility facility) {
		return facilityAmenityDAO.getAmenityByFacility(facility);
	}

	@Override
	public void removeAmenityByFacility(Facility facility)
			throws RoomingException {
		facilityAmenityDAO.removeAmenityByFaility(facility);
	}


}
