package com.iti.rooming.dataaccess.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.dto.wrapper.FacilitySelectionCriteriaWrapper;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.SelectionView;
import com.iti.rooming.dataaccess.dao.SelectionViewDAO;

@Stateless
public class SelectionViewDAOImpl extends BaseDAO implements SelectionViewDAO {

	@Override
	public List<SelectionView> getAllFacilitiesWithCriteria(
			FacilitySelectionCriteriaWrapper facilitySelectionCriteriaWrapper) {
		String sql = "SELECT new SelectionView(v.facility ,v.longitude, v.latitude, min(v.price), count(v.roomId), v.profileImage,v.facilityCoverPhoto, v.description, v.bookmark, v.subscription) FROM SelectionView v"
				+ "	WHERE v.price BETWEEN :minPrice AND :maxPrice"
				+ " AND v.longitude BETWEEN :minLon AND :maxLon"
				+ " AND v.latitude BETWEEN :minLat AND :maxLat"
				+ " AND v.facility.gender=:gender "
				+ "group by  v.facility, v.longitude, v.latitude,  v.profileImage,v.facilityCoverPhoto, v.description";

		Query query = em.createQuery(sql, SelectionView.class);

		query.setParameter("minPrice", facilitySelectionCriteriaWrapper
				.getRoomPriceWrapper().getMinPrice());
		query.setParameter("maxPrice", facilitySelectionCriteriaWrapper
				.getRoomPriceWrapper().getMaxPrice());

		query.setParameter("minLon", facilitySelectionCriteriaWrapper
				.getBounds().getMinLon());
		query.setParameter("maxLon", facilitySelectionCriteriaWrapper
				.getBounds().getMaxLon());

		query.setParameter("minLat", facilitySelectionCriteriaWrapper
				.getBounds().getMinLat());
		query.setParameter("maxLat", facilitySelectionCriteriaWrapper
				.getBounds().getMaxLat());
		query.setParameter("gender", facilitySelectionCriteriaWrapper.getGender());
		List<SelectionView> list = query.getResultList();
		return list;
	}

}
