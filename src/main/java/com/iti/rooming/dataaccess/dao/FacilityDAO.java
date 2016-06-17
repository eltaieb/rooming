package com.iti.rooming.dataaccess.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityImage;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.entity.SelectionView;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.Bounds;

@Local
public interface FacilityDAO  {

	public List<Facility> getAll(Bounds bounds) throws RoomingException;

	public List<Facility> getAllWithCritria() throws RoomingException;
	public Long getNumOfFacilityRows(Map<String, Object> filters);
	
	public List<Facility> getAllFacility(Bounds bounds)  throws RoomingException;
	
	public Facility addOrUpdateFacility(Facility facility) throws RoomingException;

	public List<Facility> loadFacility(int first, int pageSize,
			String sortField, boolean b, Map<String, Object> filters);
	public void addOrUpdateFacilityImages(List<FacilityImage> images)throws RoomingException;

	public FacilityImage addOrUpdateFacilityImage(FacilityImage facilityImage);

	public Facility find(Long id);

	public List<FacilityImage> getFacilityImageByFacility(Facility facility);

	public List<SelectionView> getFacilityByBoundaryAndPrice(
			BigDecimal minimumPrice, BigDecimal maximumPrice,
			Bounds boundary);

	public List<Facility> getFacilityBySelectedView(
			List<SelectionView> selectedFacilityIds);

	public List<Facility> getAll(RoomAdvertiser roomAdvertiser);

	public List<Facility> getAllFacilitiesOf(RoomAdvertiser roomAdvertiser);
	
}
