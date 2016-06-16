package com.iti.rooming.dataaccess.daoimpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iti.rooming.common.dto.RoomSeekerWrapper;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilitySeeker;
import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.dataaccess.dao.SeekerFacilityDAO;

@Stateless
public class SeekerFacilityDAOImpl extends BaseDAO implements SeekerFacilityDAO {

	@PersistenceContext
	EntityManager entity;

	@Override
	public void subscribeSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException {
		String sql = "SELECT f FROM FacilitySeeker f WHERE f.facility.id=:facilityID AND f.roomSeeker.id=:seekerID";
		Query query = em.createQuery(sql);
		query.setParameter("facilityID", roomSeekerWrapper.getFacilityID());
		query.setParameter("seekerID", roomSeekerWrapper.getRoomSeekerID());
		if (query.getResultList().size() > 0)
			return;
		Facility facility = new Facility();
		RoomSeeker roomSeeker = new RoomSeeker();
		facility.setId(roomSeekerWrapper.getFacilityID());
		roomSeeker.setId(roomSeekerWrapper.getRoomSeekerID());
		FacilitySeeker facilitySeeker = new FacilitySeeker();
		facilitySeeker.setFacility(facility);
		facilitySeeker.setRoomSeeker(roomSeeker);
		entity.persist(facilitySeeker);

	}

	@Override
	public void unsubscribeSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException {
		Query query = entity
				.createQuery("DELETE FROM FacilitySeeker fs WHERE fs.facility=:facility AND fs.roomSeeker=:seeker");
		Facility facility = new Facility();
		RoomSeeker roomSeeker = new RoomSeeker();
		facility.setId(roomSeekerWrapper.getFacilityID());
		roomSeeker.setId(roomSeekerWrapper.getRoomSeekerID());
		query.setParameter("facility", facility);
		query.setParameter("seeker", roomSeeker);
		query.executeUpdate();

	}

	@Override
	public void updateSubscriptionSeeker(RoomSeekerWrapper roomSeekerWrapper)
			throws RoomingException {
		Query query = entity
				.createQuery("UPDATE FacilitySeeker fs SET fs.facility=:facility,fs.roomSeeker=:seeker");
		Facility facility = new Facility();
		RoomSeeker roomSeeker = new RoomSeeker();
		facility.setId(roomSeekerWrapper.getFacilityID());
		roomSeeker.setId(roomSeekerWrapper.getRoomSeekerID());
		query.setParameter("facility", facility);
		query.setParameter("seeker", roomSeeker);
		query.executeUpdate();

	}

}
