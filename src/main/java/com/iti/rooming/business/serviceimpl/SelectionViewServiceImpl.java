package com.iti.rooming.business.serviceimpl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.iti.rooming.business.service.SelectionViewService;
import com.iti.rooming.common.dto.FacilitySelectionCriteria;
import com.iti.rooming.common.dto.wrapper.FacilitySelectionCriteriaWrapper;
import com.iti.rooming.common.dto.wrapper.FacilityWrapper;
import com.iti.rooming.common.dto.wrapper.RoomPriceWrapper;
import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Role;
import com.iti.rooming.common.entity.SelectionView;
import com.iti.rooming.common.utils.Bounds;
import com.iti.rooming.common.utils.GeoLocation;
import com.iti.rooming.dataaccess.dao.SelectionViewDAO;

@Stateless
public class SelectionViewServiceImpl implements SelectionViewService {

	@EJB
	private SelectionViewDAO selectionViewDAO;

	@Override
	public List<FacilityWrapper> getAllFacilitiesWithCriteria(
			FacilitySelectionCriteria facilitySelectionCriteria) {

		FacilitySelectionCriteriaWrapper facilitySelectionCriteriaWrapper = convertFacilityCriteriaToWrapper(facilitySelectionCriteria);
		List<SelectionView> selectionViews = selectionViewDAO
				.getAllFacilitiesWithCriteria(facilitySelectionCriteriaWrapper);

		return getFacilityWithMathedCriteria(selectionViews,
				facilitySelectionCriteria);
	}

	private List<FacilityWrapper> getFacilityWithMathedCriteria(
			List<SelectionView> selectionViews,
			FacilitySelectionCriteria facilitySelectionCriteria) {
		List<Long> amenities = facilitySelectionCriteria.getAmenities();
		List<Long> roles = facilitySelectionCriteria.getRoles();

		List<FacilityWrapper> facilityWrappers = new LinkedList<>();
		for (int i = 0; i < selectionViews.size(); i++) {
			Facility f = selectionViews.get(i).getFacility();
			List<Amenity> amenitiesOfFacility = f.getAmenities();
			for (int a = 0; a < amenitiesOfFacility.size(); a++) {
				if (amenities.contains(amenitiesOfFacility.get(a))) {
					f.setMatches(f.getMatches() + 1);
				}
			}
			List<Role> rolesOfFacility = f.getRoles();
			for (int r = 0; r < rolesOfFacility.size(); r++) {
				if (roles.contains(rolesOfFacility.get(r))) {
					f.setMatches(f.getMatches() + 1);
				}
			}
			if (f.getMatches() >= 0) {

				SelectionView selectionView = selectionViews.get(i);
				FacilityWrapper facilityWrapper = new FacilityWrapper();

				facilityWrapper.setId(f.getId());
				facilityWrapper.setDescription(selectionView.getDescription());
				facilityWrapper.setFacilityCover(selectionView
						.getFacilityCoverPhoto());
				facilityWrapper
						.setProfileImage(selectionView.getProfileImage());
				facilityWrapper.setLon((selectionView.getLongitude() * 180)
						/ Math.PI);
				facilityWrapper.setLat((selectionView.getLatitude() * 180)
						/ Math.PI);
				facilityWrapper.setMinPrice(selectionView.getPrice());
				facilityWrapper.setOwnerId(selectionView.getOnwerId());

				if (selectionView.getBookmark() > 0) {
					facilityWrapper.setIsFavourite(true);
				} else {
					facilityWrapper.setIsFavourite(false);
				}
				if (selectionView.getSubscription() > 0) {
					facilityWrapper.setIsSubscribe(true);
				} else {
					facilityWrapper.setIsSubscribe(false);
				}
				facilityWrappers.add(facilityWrapper);
			}
		}

		/*
		 * List<Facility> facilities = selectionViews .parallelStream() .map(s
		 * -> s.getFacility()) .filter(f -> { Integer matchesRoles =
		 * f.getRoles().parallelStream() .filter(fr -> roles.contains(fr))
		 * .collect(Collectors.toList()).size();
		 * 
		 * Integer matchesAmenities = f.getAmenities() .parallelStream()
		 * .filter(fa -> amenities.contains(fa))
		 * .collect(Collectors.toList()).size();
		 * 
		 * f.setMatches(matchesAmenities + matchesRoles + 0L); if
		 * (f.getMatches() > 0) return true; else return false;
		 * 
		 * }).collect(Collectors.toList());
		 */
		Collections.sort(facilityWrappers);

		return facilityWrappers;
	}

	private FacilitySelectionCriteriaWrapper convertFacilityCriteriaToWrapper(
			FacilitySelectionCriteria facilitySelectionCriteria) {
		FacilitySelectionCriteriaWrapper facilitySelectionCriteriaWrapper = new FacilitySelectionCriteriaWrapper();
		GeoLocation goGeoLocation = new GeoLocation(
				facilitySelectionCriteria.getLon(),
				facilitySelectionCriteria.getLat(),
				facilitySelectionCriteria.getArea());

		Bounds bounds = goGeoLocation.getBounds();
		facilitySelectionCriteriaWrapper.setBounds(bounds);

		RoomPriceWrapper roomPriceWrapper = new RoomPriceWrapper();
		roomPriceWrapper.setMaxPrice(facilitySelectionCriteria.getMaxPrice());
		roomPriceWrapper.setMinPrice(facilitySelectionCriteria.getMinPrice());
		facilitySelectionCriteriaWrapper.setRoomPriceWrapper(roomPriceWrapper);

		return facilitySelectionCriteriaWrapper;
	}

}
