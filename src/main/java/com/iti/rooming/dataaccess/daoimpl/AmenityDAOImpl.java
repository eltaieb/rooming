package com.iti.rooming.dataaccess.daoimpl;


import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.dataaccess.dao.AmenityDAO;

@Stateless
public class AmenityDAOImpl extends BaseDAO implements AmenityDAO {


	@Override
	public Amenity getAmenity(Long id) {
		return (Amenity) super.find(Amenity.class, id);
	}

	@Override
	public List<Amenity> getAll() {
		return super.getAll(Amenity.class);
	}

	@Override
	public void remove(Amenity amenity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Amenity find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Amenity amenity) {
		// TODO Auto-generated method stub
		super.saveOrUpdate(amenity);
		
	}

	@Override
	public List<Amenity> getAmenityByFacility(Facility facility) {
		Query query = em.createQuery("SELECT a.amenity FROM FacilityAmenity a WHERE a.facility = :facility ");
		query = query.setParameter("facility", facility);
		return query.getResultList();
	}

	@Override
	public List<Amenity> loadAmenities(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.load(Amenity.class, first, pageSize, sortField, ascending, filters);
	}

	@Override
	public int getNumOfAmenitiesRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.getNumOfRows(Amenity.class, filters);
	}

	@Override
	public void updateAmenity(Amenity amenity) {
		// TODO Auto-generated method stub
		super.persist(amenity);
		
	}
	

}
