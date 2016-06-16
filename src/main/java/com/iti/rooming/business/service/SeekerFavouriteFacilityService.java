package com.iti.rooming.business.service;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.dto.wrapper.FacilityWrapper;
import com.iti.rooming.common.entity.SeekerFavouriteFacility;

@Local
public interface SeekerFavouriteFacilityService {
	public void addFavourite(SeekerFavouriteFacility seekerFavouriteFacility);


	public void removeFavourite(Long seekerID, Long facilityID);


	public List<FacilityWrapper> getAll(Long seekerId);
}
