package com.iti.rooming.dataaccess.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.dto.wrapper.FavouriteWrapper;
import com.iti.rooming.common.entity.SeekerFavouriteFacility;
import com.iti.rooming.common.entity.SelectionView;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.dataaccess.dao.SeekerFavouriteFacilityDAO;

@Stateless
public class SeekerFavouriteFacilityDAOImpl extends BaseDAO implements
		SeekerFavouriteFacilityDAO {

	@Override
	public void addFavourite(SeekerFavouriteFacility seekerFavouriteFacility) {
		String sql = "SELECT s FROM SeekerFavouriteFacility s WHERE s.seeker.id=:seekerID AND s.facility.id=:facilityID";
		Query query = em.createQuery(sql);
		query.setParameter("seekerID", seekerFavouriteFacility.getSeeker()
				.getId());
		query.setParameter("facilityID", seekerFavouriteFacility.getFacility()
				.getId());
		if (query.getResultList().size() > 0)
			return;
		super.persist(seekerFavouriteFacility);

	}

	@Override
	public void removeFavourite(Long seekerId, Long facilityId) {
		Query query = em
				.createQuery("delete from SeekerFavouriteFacility x where x.seeker.id =:seekerId and x.facility.id =:facilityId ");
		query.setParameter("seekerId", seekerId);
		query.setParameter("facilityId", facilityId);
		query.executeUpdate();

	}

	@Override
	public List<SelectionView> getAll(Long seekerId) {
		String sql = "SELECT v FROM SelectionView v WHERE v.facility IN (SELECT s.facility from SeekerFavouriteFacility s WHERE s.seeker.id=:seekerID) GROUP BY v.facility.id";
		Query query = em.createQuery(sql, SelectionView.class);
		query.setParameter("seekerID", seekerId);
		List list = query.getResultList();
		;
		return list;
	}

}
