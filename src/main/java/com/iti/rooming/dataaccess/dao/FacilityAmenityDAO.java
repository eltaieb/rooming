package com.iti.rooming.dataaccess.dao;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityAmenity;


@Local
public interface FacilityAmenityDAO {
	public FacilityAmenity addOrUpdate(FacilityAmenity facilityAmenity);
	public List<Amenity> getAmenityByFacility (Facility facility);
	public void removeAmenityByFaility(Facility facility);
}
