package com.iti.rooming.business.service;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.dto.FacilitySelectionCriteria;
import com.iti.rooming.common.dto.wrapper.FacilityWrapper;
import com.iti.rooming.common.entity.Facility;

@Local
public interface SelectionViewService {
	public List<FacilityWrapper> getAllFacilitiesWithCriteria(
			FacilitySelectionCriteria facilitySelectionCriteria);

}
