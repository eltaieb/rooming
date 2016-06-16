package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.exception.RoomingException;

@ManagedBean
@ViewScoped
public class FacilityCriteriaManagedBean implements Serializable {

	@EJB
	RoomingManagment management;

	public RoomingManagment getManagement() {
		return management;
	}

	public void setManagement(RoomingManagment management) {
		this.management = management;
	}

	public List<Facility> findAllFacilitiesWithCriteria() {

		List<Facility> facility = new ArrayList<Facility>();
		try {
			facility = management.getAllWithCritria();
		} catch (RoomingException e) {
			e.printStackTrace();
		}

		return facility;

	}

	/*
	 * private Facility facility;
	 * 
	 * private Room room;
	 * 
	 * private List<Amenity> amenities;
	 * 
	 * private List<Amenity> selectedAmenities;
	 * 
	 * 
	 * public Facility getFacility() { return facility; }
	 * 
	 * public void setFacility(Facility facility) { this.facility = facility; }
	 * 
	 * public Room getRoom() { return room; }
	 * 
	 * public void setRoom(Room room) { this.room = room; }
	 * 
	 * public List<Amenity> getAmenities() { return amenities; }
	 * 
	 * public void setAmenities(List<Amenity> amenities) { this.amenities =
	 * amenities; }
	 * 
	 * public List<Amenity> getSelectedAmenities() { return selectedAmenities; }
	 * 
	 * public void setSelectedAmenities(List<Amenity> selectedAmenities) {
	 * this.selectedAmenities = selectedAmenities; }
	 * 
	 * @PostConstruct public void init() { room = new Room(); amenities =
	 * management.getAllAmenities(); initiateNewFacility(); } | Initialize
	 * Facility | private void initiateNewFacility() { facility = new
	 * Facility(); facility.setLan(30.890257); facility.setLon(31.707417);
	 * facility.setCountry("Egypt"); facility.setDescription("");
	 * 
	 * RoomAdvertiser rA = new RoomAdvertiser(); rA.setId(1L);
	 * facility.setRoomAdvertiser(rA); }
	 */

}
