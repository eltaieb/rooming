package com.iti.rooming.business.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityImage;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.Bounds;

@Local
public interface FacilityService  {

	public List<Facility> getAllWithCritria() throws RoomingException;
	public List<Facility> loadFacility(int first, int pageSize,
			String sortField, boolean b, Map<String, Object> filters);

	public List<Facility> getAll(Bounds bounds) throws RoomingException;
	
	public Facility findFacility(Long id);

	public Long getNumOfFacilityRows(Map<String, Object> filters);

	public Facility addOrUpdateFacility(Facility facility)
			throws RoomingException;
	
	public List<Facility> getAllFacility(Double lon, Double lat, Double area) 
			throws RoomingException;
	
	public FacilityImage addOrUpdateFacilityImage(FacilityImage facilityImage);

	public List<FacilityImage> getFacilityImagesOfFacility(Facility facility);
	
	public List<Facility> getFacilityByBoundaryAndPrice(
			BigDecimal minimumPrice, BigDecimal maximumPrice,
			Bounds boundary);
	public List<Facility> getAll(RoomAdvertiser roomAdvertiser);

	public List<Facility> getAllFacilitiesOf(RoomAdvertiser roomAdvertiser);
	



}
