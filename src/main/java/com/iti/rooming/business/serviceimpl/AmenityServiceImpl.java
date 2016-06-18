package com.iti.rooming.business.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.iti.rooming.business.service.AmenityService;
import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.dataaccess.dao.AmenityDAO;

@Stateless
public class AmenityServiceImpl implements AmenityService {
	@EJB
	private AmenityDAO amenityDAO;

	
	
	public Amenity getAmenityById(Long id){
		return amenityDAO.getAmenity(id);
	}	
	@Override
	public void saveOrUpdate(Amenity amenity) throws RoomingException {
		amenityDAO.saveOrUpdate(amenity);

	}

	@Override
	public void remove(Amenity amenity) throws RoomingException {
		amenityDAO.remove(amenity);

	}


	@Override
	public List<Amenity> getAllAmenities() {
		return amenityDAO.getAll();

	}
	@Override
	public List<Amenity> getAmenityByFacility(Facility facility) {
		return amenityDAO.getAmenityByFacility(facility);
	}
	@Override
	public List<Amenity> loadAmenities(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return amenityDAO.loadAmenities(first, pageSize, sortField, ascending, filters);
	}
	@Override
	public int getNumOfAmenitiesRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return amenityDAO.getNumOfAmenitiesRows(filters);
	}
	@Override
	public void updateAmenity(Amenity amenity) {
		// TODO Auto-generated method stub
		amenityDAO.updateAmenity(amenity);
	}
	

	

}
