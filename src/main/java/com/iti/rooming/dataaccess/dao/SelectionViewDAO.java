package com.iti.rooming.dataaccess.dao;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.dto.FacilitySelectionCriteria;
import com.iti.rooming.common.dto.wrapper.FacilitySelectionCriteriaWrapper;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.SelectionView;

@Local
public interface SelectionViewDAO {
	public List<SelectionView> getAllFacilitiesWithCriteria(
			FacilitySelectionCriteriaWrapper facilitySelectionCriteriaWrapper);
}
