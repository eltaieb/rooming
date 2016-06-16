package com.iti.rooming.dataaccess.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityAmenity;
import com.iti.rooming.dataaccess.dao.FacilityAmenityDAO;


@Stateless
public class FacilityAmenityDAOImpl  extends BaseDAO implements FacilityAmenityDAO{

	@Override
	public FacilityAmenity addOrUpdate(FacilityAmenity facilityAmenity) {
		
		return (FacilityAmenity)super.persist(facilityAmenity);
	}

	@Override
	public List<Amenity> getAmenityByFacility(Facility facility) {
		Query q = em
				.createQuery("SELECT fa.amenity FROM FacilityAmenity fa WHERE fa.facility = :facility");
				q.setParameter("facility",facility);
		return (List<Amenity>) q.getResultList();

	}

	@Override
	public void removeAmenityByFaility(Facility facility) {
		if (facility != null) {
			Query query = em
					.createQuery("DELETE FROM FacilityAmenity fa WHERE fa.facility = :facility");
			query = query.setParameter("facility", facility);
			query.executeUpdate();
		}
	}

}
