package com.iti.rooming.dataaccess.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.entity.Facility;


@Local
public interface AmenityDAO  {
	public Amenity getAmenity(Long id);
	public List<Amenity> getAll();
	public void remove(Amenity amenity);
	public Amenity find(Long id);
	public void saveOrUpdate(Amenity amenity);
	public List<Amenity> getAmenityByFacility(Facility facility);
	List<Amenity> loadAmenities(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);
	int getNumOfAmenitiesRows(Map<String, Object> filters);
	public void updateAmenity(Amenity amenity);
}
