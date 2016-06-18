package com.iti.rooming.business.service;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.exception.RoomingException;

@Local
public interface AmenityService {

	public List<Amenity> getAllAmenities ();
	public Amenity getAmenityById(Long id);
	public void saveOrUpdate(Amenity amenity) throws RoomingException;
	public void remove(Amenity amenity) throws RoomingException;
	public List<Amenity> getAmenityByFacility(Facility facility);
	List<Amenity> loadAmenities(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);
	int getNumOfAmenitiesRows(Map<String, Object> filters);
	public void updateAmenity(Amenity amenity);

}
