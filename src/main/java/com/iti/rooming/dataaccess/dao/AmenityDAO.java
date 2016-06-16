package com.iti.rooming.dataaccess.dao;

import java.util.List;

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
}
