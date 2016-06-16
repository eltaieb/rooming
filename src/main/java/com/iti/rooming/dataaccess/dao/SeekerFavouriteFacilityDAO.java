package com.iti.rooming.dataaccess.dao;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.dto.wrapper.FavouriteWrapper;
import com.iti.rooming.common.entity.SeekerFavouriteFacility;
import com.iti.rooming.common.entity.SelectionView;

@Local
public interface SeekerFavouriteFacilityDAO {
	public void addFavourite(SeekerFavouriteFacility seekerFavouriteFacility);

	public void removeFavourite(Long seekerID, Long facilityID);

	public List<SelectionView> getAll(Long seekerId);


}
