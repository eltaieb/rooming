package com.iti.rooming.business.service;

import java.util.List;

import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityAmenity;
import com.iti.rooming.common.exception.RoomingException;

public interface FacilityAmenityService {
	
	public FacilityAmenity addOrUpdate (FacilityAmenity value) throws RoomingException;

	public List<Amenity> getAmenitiesOfFacility(Facility facility);

	public void removeAmenityByFacility(Facility facility) throws RoomingException; 

}
