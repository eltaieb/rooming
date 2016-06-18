package com.iti.rooming.dataaccess.daoimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.dto.FacilityCity;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityImage;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.entity.SelectionView;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.Bounds;
import com.iti.rooming.common.utils.GeoLocation;
import com.iti.rooming.dataaccess.dao.FacilityDAO;

@Stateless
public class FacilityDAOImpl extends BaseDAO implements FacilityDAO {

	public Facility addOrUpdateFacility(Facility facility)
			throws RoomingException {

		facility = (Facility) super.persist(facility);
		return facility;
	}

	@Override
	public List<Facility> getAll(Bounds bounds) throws RoomingException {
		Query q = em
				.createQuery("SELECT f.lon, f.lan, f.city, f.description FROM Facility f WHERE (f.lon between :minLon AND :maxLon) AND (f.lan between :minLat AND :maxLat)");
		q.setParameter("minLon", bounds.getMinLon());
		q.setParameter("maxLon", bounds.getMaxLon());
		q.setParameter("minLat", bounds.getMinLat());
		q.setParameter("maxLat", bounds.getMaxLat());
		List<Facility> facilities = q.getResultList();
		return facilities;
	}

	@Override
	public Long getNumOfFacilityRows(Map<String, Object> filters) {
		Query q = em.createQuery("SELECT COUNT(f.id) FROM Facility f");
		Long rows = (Long) q.getSingleResult();
		return rows;
	}

	@Override
	public List<Facility> loadFacility(int first, int pageSize,
			String sortField, boolean b, Map<String, Object> filters) {
		return load(Facility.class, first, pageSize, sortField, b, filters);
	}

	@Override
	public void addOrUpdateFacilityImages(List<FacilityImage> images)
			throws RoomingException {
		for (FacilityImage image : images) {
			super.persist(image);
		}

	}

	@Override
	public FacilityImage addOrUpdateFacilityImage(FacilityImage facilityImage) {
		return (FacilityImage) super.persist(facilityImage);
	}

	@Override
	public List<Facility> getAllFacility(Bounds bounds) throws RoomingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Facility find(Long id) {
		return (Facility) super.find(Facility.class, id);
	}

	@Override
	public List<FacilityImage> getFacilityImageByFacility(Facility facility) {
		Query query = em
				.createQuery("SELECT f FROM FacilityImage f WHERE f.facility = :facility ");
		query = query.setParameter("facility", facility);
		return query.getResultList();
	}

	@Override
	public List<Facility> getAllWithCritria() throws RoomingException {
//		List<Long> aminityList = new ArrayList();
//		aminityList.add((long) 1);
//		aminityList.add((long) 2);
//		aminityList.add((long) 3);
//		aminityList.add((long) 6);
//		aminityList.add((long) 7);
//
//		List<Long> rolesList = new ArrayList();
//		rolesList.add((long) 1);
//		rolesList.add((long) 2);
//		rolesList.add((long) 3);
//		rolesList.add((long) 4);
//		rolesList.add((long) 5);
//
//		GeoLocation geo = new GeoLocation(31.703443, 30.8992618, 900000.00);
//		Bounds bounds1 = geo.getBounds();
//		Query query = em
//				.createQuery("select DISTINCT f "
//						+ " FROM FacilityAmenity fa  JOIN fa.facility f"
//						+ " , FacilityRole fr JOIN fr.facility ffr"
//						+ " WHERE f.lan BETWEEN :minLat AND :maxLat and f.lon BETWEEN :minLon AND :maxLon"
//						+ " OR   fa.amenity.id IN :aminityList"
//						+ " AND f = ffr" + " AND fr.role.id IN :rolesList");
//
//		query.setParameter("minLat", bounds1.getMinLat());
//		query.setParameter("maxLat", bounds1.getMaxLat());
//		query.setParameter("minLon", bounds1.getMinLon());
//		query.setParameter("maxLon", bounds1.getMaxLon());
//		query.setParameter("aminityList", aminityList);
//		query.setParameter("rolesList", rolesList);
//
//		List<Facility> facilities = query.getResultList();
		return null;
	}

	@Override
	public List<SelectionView> getFacilityByBoundaryAndPrice(
			BigDecimal minimumPrice, BigDecimal maximumPrice, Bounds boundary) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT sv FROM SelectionView sv WHERE ");
		queryString.append("sv.price BETWEEN :minPrice AND :maxPrice ");
		queryString.append("AND sv.longitude BETWEEN :minLon AND :maxLon ");
		queryString.append("AND sv.latitude BETWEEN :minLat AND :maxLat ");
		Query q = em.createQuery(queryString.toString());
		q.setParameter("minPrice", minimumPrice);
		q.setParameter("maxPrice", maximumPrice);
		q.setParameter("minLon", boundary.getMinLon());
		q.setParameter("maxLon", boundary.getMaxLon());
		q.setParameter("minLat", boundary.getMinLat());
		q.setParameter("maxLat", boundary.getMaxLat());

		List<SelectionView> resultList = (List<SelectionView>) q
				.getResultList();
		return resultList;
	}

	@Override
	public List<Facility> getFacilityBySelectedView(
			List<SelectionView> selectedFacilityIds) {
		return null;
	}

	@Override
	public List<Facility> getAll(RoomAdvertiser roomAdvertiser) {
		String sql = "SELECT f" + " FROM Facility f"
				+ " WHERE f.roomAdvertiser=:roomAdvertiser";
		Query query = em.createQuery(sql, Facility.class);
		query.setParameter("roomAdvertiser", roomAdvertiser);
		return query.getResultList();
	}

	@Override
	public void removeImageNotInFacilityImages(
			List<FacilityImage> facilityImages, Facility facility) {
		if (facilityImages != null && facilityImages.size() > 0) {
			Query query = em
					.createQuery("DELETE FROM FacilityImage fi WHERE fi.facility = :facility AND fi NOT IN ( :images )");
			query = query.setParameter("images", facilityImages);
			query.setParameter("facility", facility).executeUpdate();
		} else {// Case Rooms existed but then where all deleted
			Query query = em
					.createQuery("DELETE FROM FacilityImage fi WHERE fi.facility = :facility ");
			query.setParameter("facility", facility).executeUpdate();
		}
	}

	@Override
	public List<Facility> getAllFacilitiesOf(RoomAdvertiser roomAdvertiser) {
		String sql = "SELECT f FROM Facility f WHERE f.roomAdvertiser=:roomAdvertiser";
		Query query = em.createQuery(sql, Facility.class);
		query.setParameter("roomAdvertiser", roomAdvertiser);
		return query.getResultList();
	}

	@Override
	public List<FacilityCity> getFacilitiesInCities() {
		String sql = "SELECT f.city, COUNT(f.id) FROM Facility f GROUP BY f.city";
		Query query = em.createQuery(sql);
		List result = query.getResultList();
		List<FacilityCity> facilitycities = new LinkedList<FacilityCity>();
		result.parallelStream().forEach(
				f -> {
					Object[] objects = (Object[]) f;
					facilitycities.add(new FacilityCity((Long) objects[1],
							objects[0].toString()));
				});
		return facilitycities;
	}
}
