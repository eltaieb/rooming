package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.Facility;

@ManagedBean
@ViewScoped
public class FacilityViewManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	RoomingManagment management;

	private Facility facility;
	private MapModel apartmentLocation;

	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public MapModel getApartmentLocation() {
		return apartmentLocation;
	}

	public void setApartmentLocation(MapModel apartmentLocation) {
		this.apartmentLocation = apartmentLocation;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	@PostConstruct
	public void init() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String parameterOne = params.get("facilityId");
		if (parameterOne != null) {
			try {
				Long facilityId = new Long(parameterOne);

				facility = management.getFacility(facilityId);
				if (facility == null) {
					handleNonExistingFacility();
					return;
				}
				if (facility.getGender().equalsIgnoreCase("m"))
					gender = "Male .";
				else
					gender = "Female .";

				LatLng latLng = new LatLng(facility.getLan(), facility.getLon());
				String location = facility.getStreet() + " "
						+ facility.getCity() + " " + facility.getCountry();
				apartmentLocation = new DefaultMapModel();
				apartmentLocation.addOverlay(new Marker(latLng, location));
			} catch (NumberFormatException e) {
				handleNonExistingFacility();
			}
		} else {
			handleNonExistingFacility();
		}
	}

	void handleNonExistingFacility() {
		facility = new Facility();
		apartmentLocation = new DefaultMapModel();

	}

}
