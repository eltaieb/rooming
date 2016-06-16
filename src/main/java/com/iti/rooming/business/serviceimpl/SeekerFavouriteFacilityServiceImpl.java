package com.iti.rooming.business.serviceimpl;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.iti.rooming.business.service.SeekerFavouriteFacilityService;
import com.iti.rooming.common.dto.FacilitySelectionCriteria;
import com.iti.rooming.common.dto.wrapper.FacilityWrapper;
import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Role;
import com.iti.rooming.common.entity.SeekerFavouriteFacility;
import com.iti.rooming.common.entity.SelectionView;
import com.iti.rooming.dataaccess.dao.SeekerFavouriteFacilityDAO;
import com.iti.rooming.dataaccess.daoimpl.SeekerFacilityDAOImpl;

@Stateless
public class SeekerFavouriteFacilityServiceImpl implements
		SeekerFavouriteFacilityService {

	@EJB
	private SeekerFavouriteFacilityDAO seekerFavouriteFacilityDAO;

	@Override
	public void addFavourite(SeekerFavouriteFacility seekerFavouriteFacility) {
		seekerFavouriteFacilityDAO.addFavourite(seekerFavouriteFacility);
	}

	@Override
	public void removeFavourite(Long seekerID, Long facilityID) {
		seekerFavouriteFacilityDAO.removeFavourite(seekerID, facilityID);

	}

	@Override
	public List<FacilityWrapper> getAll(Long seekerId) {
		List<SelectionView> selectionViews = seekerFavouriteFacilityDAO
				.getAll(seekerId);

		List<FacilityWrapper> facilityWrappers = new LinkedList<>();
		for (int i = 0; i < selectionViews.size(); i++) {
			Facility f = selectionViews.get(i).getFacility();
			SelectionView selectionView = selectionViews.get(i);
			FacilityWrapper facilityWrapper = new FacilityWrapper();

			facilityWrapper.setId(f.getId());
			facilityWrapper.setDescription(selectionView.getDescription());
			facilityWrapper.setFacilityCover(selectionView
					.getFacilityCoverPhoto());
			facilityWrapper.setProfileImage(selectionView.getProfileImage());
			facilityWrapper.setLon(selectionView.getLongitude());
			facilityWrapper.setLat(selectionView.getLatitude());
			facilityWrapper.setMinPrice(selectionView.getPrice());
			facilityWrapper.setIsFavourite(true);

			if (selectionView.getSubscription() > 0) {
				facilityWrapper.setIsSubscribe(true);
			} else {
				facilityWrapper.setIsSubscribe(false);
			}
			facilityWrappers.add(facilityWrapper);
		}
		return facilityWrappers;
	}

}
