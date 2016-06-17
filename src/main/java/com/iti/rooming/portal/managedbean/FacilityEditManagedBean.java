package com.iti.rooming.portal.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.config.Configurations;
import com.iti.rooming.common.dto.geolocation.GeoLocationDetails;
import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityImage;
import com.iti.rooming.common.entity.Role;
import com.iti.rooming.common.entity.Room;
import com.iti.rooming.common.entity.RoomImage;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.MapUtil;
import com.iti.rooming.common.utils.Utils;

@ManagedBean
@ViewScoped
public class FacilityEditManagedBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -8246284800381776648L;
	private boolean skip;
	@EJB
	RoomingManagment management;

	private MapModel location;

	private Facility facility;

	private Room room;

	private List<Amenity> amenities;
	private List<Amenity> selectedAmenities;

	private List<Role> roles;
	private List<Role> selectedRoles;

	/* | SETTERS AND GTTERS | */

	public List<Amenity> getSelectedAmenities() {
		return selectedAmenities;
	}

	public void setSelectedAmenities(List<Amenity> selectedAmenities) {
		this.selectedAmenities = selectedAmenities;
	}

	public List<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<Amenity> amenities) {
		this.amenities = amenities;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setManagement(RoomingManagment management) {
		this.management = management;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;

	}

	public MapModel getApartmentLocation() {
		return location;
	}

	public void setApartmentLocation(MapModel location) {
		this.location = location;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<Role> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	/* | Get Facility From Request Params | */
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
				} else {
					initializeViewComponents();
				}
			} catch (NumberFormatException e) {
				handleNonExistingFacility();
			}
		} else {
			handleNonExistingFacility();
		}
	}

	/* | Facility Not Found Handler | */
	private void handleNonExistingFacility() {
		newRoomInstance();
		facility = new Facility();
		location = new DefaultMapModel();
		amenities = management.getAllAmenities();
		roles = management.getAllRoles();
	}

	/* | Facility Found Handler .. Initializing components | */
	private void initializeViewComponents() {
		newRoomInstance();
		// Set The Facility Found To The View ..
		// SET LOCATION
		LatLng latLng = new LatLng(facility.getLan(), facility.getLon());
		String locationName = facility.getStreet() + " " + facility.getCity()
				+ " " + facility.getCountry();
		location = new DefaultMapModel();
		location.addOverlay(new Marker(latLng, locationName));

		// SET ROLES AND AMENITIES
		amenities = management.getAllAmenities();
		selectedAmenities = facility.getAmenities();
		if (amenities == null)
			amenities = new ArrayList<Amenity>();
		if (selectedAmenities == null)
			selectedAmenities = new ArrayList<Amenity>();

		roles = management.getAllRoles();
		selectedRoles = facility.getRoles();
		if (roles == null)
			roles = new ArrayList<Role>();
		if (selectedRoles == null)
			selectedRoles = new ArrayList<Role>();
	}

	/* | Add Room To Facility | */
	public void appendRoomToFacility() {
		System.out.println(room.toString());
		room.setIsDeleted(Boolean.FALSE);
		room.setAdvertiseDate(new Date());
		room.setExpandable(Boolean.FALSE);
		room.setDuration(0);
		facility.getRooms().add(room);
		newRoomInstance();
	}

	/* | Instantiate a new room | */
	public void newRoomInstance() {
		room = new Room();
	}

	/* | ON DELETE CLICK | */
	public void deleteAction(Room room) {
		facility.getRooms().remove(room);
	}

	/* | ON DELETE IMAGE CLICK | */
	public void deleteImageAction(FacilityImage deletedImage) {
		int index = -1;
		for (int loop = 0; loop < facility.getImages().size(); loop++) {
			FacilityImage image = facility.getImages().get(loop);
			if (image.equals(deletedImage)) {
				index = loop;
				break;
			}
		}
		if (index > -1) {
			facility.getImages().remove(index);
		}
	}

	/* | Set Room Image | */
	public void uploadRoomImage(FileUploadEvent event) {
		try {
			Utils.copyFile(
					Configurations.getProperty(Configurations.ROOM_PATH), event
							.getFile().getFileName(), event.getFile()
							.getInputstream());

			RoomImage roomImage = new RoomImage();
			roomImage.setImage(event.getFile().getFileName());
			room.getImages().add(roomImage);
			System.out.println("FILE UPLOAD FN >> UPLOADED A FILE");
			FacesMessage msg = new FacesMessage("Success! ", event.getFile()
					.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* | Set Facility Images | */
	public void uploadFacilityImage(FileUploadEvent event) {
		try {

			Utils.copyFile(Configurations
					.getProperty(Configurations.FACILITY_IMAGES_PATH), event
					.getFile().getFileName(), event.getFile().getInputstream());

			FacilityImage image = new FacilityImage();
			image.setImage(event.getFile().getFileName());

			facility.getImages().add(image);
			System.out.println("FILE UPLOAD FN >> UPLOADED A FILE");
			FacesMessage msg = new FacesMessage("Success! ", event.getFile()
					.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* | Sets Marker on map from map onclick -point select- event | */
	public void onPointSelect(PointSelectEvent event) {

		LatLng latlng = event.getLatLng();
		GeoLocationDetails address = MapUtil.getLocationFromLatLng(latlng);

		String fullAddress = setFacilityLocationDetails(address);
		// Set Marker
		setNewMarker(latlng, fullAddress);
	}

	/* | Marker on map Setter | */
	public void setNewMarker(LatLng latlng, String fullAddress) {
		location.getMarkers().clear();
		location.addOverlay(new Marker(latlng, fullAddress));
	}

	/*
	 * | Sets Facility location : (country , city , longitude , latitude ,
	 * street , postal code ) |
	 */
	private String setFacilityLocationDetails(GeoLocationDetails address) {

		facility.setLan(address.getResults().get(0).getGeometry().getLocation()
				.getLat());
		facility.setLon(address.getResults().get(0).getGeometry().getLocation()
				.getLng());
		facility.setCountry(address.getResults()
				.get(address.getResults().size() - 1).getAddressComponents()
				.get(0).getLongName());
		facility.setCity(address.getResults()
				.get(address.getResults().size() - 2).getAddressComponents()
				.get(0).getShortName());
		facility.setStreet(address.getResults().get(0).getAddressComponents()
				.get(0).getLongName());

		System.out.println(facility.toString());
		return address.getResults().get(0).getFormattedAddress();
	}

	/* | ON SUBMIT CLICK | */
	public void save() {
		// update map view
		try {
			facility.setIsDeleted(Boolean.FALSE);
			facility = management.updateFacility(facility, selectedAmenities,
					selectedRoles);
		} catch (RoomingException e1) {
			FacesMessage msg = new FacesMessage("Failure",
					"Sorry a problem occured while updating your facility ! ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		FacesMessage msg = new FacesMessage("Successful",
				"Your Facility Was Updated Successfully ! ");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
