package com.iti.rooming.business.serviceimpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.iti.rooming.business.service.FacilityService;
import com.iti.rooming.common.dto.FacilityCity;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityImage;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.entity.SelectionView;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.Bounds;
import com.iti.rooming.dataaccess.dao.FacilityDAO;

@Stateless
public class FacilityServiceImp implements FacilityService {

	@EJB
	private FacilityDAO facilityDAO;

	public Facility addOrUpdateFacility(Facility facility)
			throws RoomingException {
		return facilityDAO.addOrUpdateFacility(facility);
	}

	@Override
	public Long getNumOfFacilityRows(Map<String, Object> filters) {
		return facilityDAO.getNumOfFacilityRows(filters);
	}

	@Override
	public List<Facility> loadFacility(int first, int pageSize,
			String sortField, boolean b, Map<String, Object> filters) {
		return facilityDAO.loadFacility(first, pageSize, sortField, b, filters);
	}

	@Override
	public List<Facility> getAll(Bounds bounds) throws RoomingException {
		return facilityDAO.getAll(bounds);
	}

	@Override
	public FacilityImage addOrUpdateFacilityImage(FacilityImage facilityImage) {
		return facilityDAO.addOrUpdateFacilityImage(facilityImage);
	}

	@Override
	public List<Facility> getAllFacility(Double lon, Double lat, Double area)
			throws RoomingException {
		return null;
	}

	@Override
	public Facility findFacility(Long id) {
		return facilityDAO.find(id);
	}

	@Override
	public List<FacilityImage> getFacilityImagesOfFacility(Facility facility) {
		return facilityDAO.getFacilityImageByFacility(facility);
	}

	@Override
	public List<Facility> getAllWithCritria() throws RoomingException {

		return facilityDAO.getAllWithCritria();
	}

	@Override
	public List<Facility> getFacilityByBoundaryAndPrice(
			BigDecimal minimumPrice, BigDecimal maximumPrice, Bounds boundary) {
		List<SelectionView> selectedFacilityIds = facilityDAO
				.getFacilityByBoundaryAndPrice(minimumPrice, maximumPrice,
						boundary);
		return facilityDAO.getFacilityBySelectedView(selectedFacilityIds);
	}

	@Override
	public List<Facility> getAll(RoomAdvertiser roomAdvertiser) {
		return facilityDAO.getAll(roomAdvertiser);
	}

	@Override
	public void removeDeletedFacilityImages(List<FacilityImage> facilityImages,
			Facility facility) throws RoomingException {
		facilityDAO.removeImageNotInFacilityImages(facilityImages, facility);

	}

	@Override
	public List<Facility> getAllFacilitiesOf(RoomAdvertiser roomAdvertiser) {
		return facilityDAO.getAllFacilitiesOf(roomAdvertiser);
	}

	@Override
	public List<FacilityCity> getFacilitiesInCities() {
		return facilityDAO.getFacilitiesInCities();
	}

}
