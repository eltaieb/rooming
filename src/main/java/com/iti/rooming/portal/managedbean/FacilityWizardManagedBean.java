package com.iti.rooming.portal.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
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
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.entity.RoomImage;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.MapUtil;
import com.iti.rooming.common.utils.Utils;

@ManagedBean
@ViewScoped
public class FacilityWizardManagedBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -8246284800381776648L;
	private boolean skip;
	@EJB
	RoomingManagment management;

	private MapModel apartmentLocation;

	private Facility facility;

	private FacilityImage coverPhoto;
	private Room room;

	private List<Amenity> amenities;
	private List<Amenity> selectedAmenities;

	private List<Role> roles;
	private List<Role> selectedRoles;

	/* | SETTERS AND GTTERS | */

	public RoomingManagment getManagement() {
		return management;
	}

	public String getOverAllLocation() {
		//GeoLocationDetails location = MapUtil.getLocationFromAddress(facility);
		//location.toString();
		return " " + facility.getBuildingNumber() + " , "
				+ facility.getStreet() + " , " + facility.getCity() + "  "
				+ facility.getCountry() + " .";
	}

	public String getOverAllRooms() {
		return " " + facility.getRooms().size() + " . ";
	}

	public String getOverAllRoleAndGender() {
		StringBuffer roles = new StringBuffer();
		int index = 0;
		for (; index < selectedRoles.size(); index++) {
			if (index == selectedRoles.size() - 1)
				roles.append(selectedRoles.get(index).getName() + " .");
			else
				roles.append(selectedRoles.get(index).getName() + " , ");
		}

		String gender = "";
		if (facility.getGender().equalsIgnoreCase("m"))
			gender = "Male";
		else
			gender = "Female";
		return " " + gender + "\n" + roles.toString();
	}

	public String getOverAllAmenities() {
		StringBuffer amenities = new StringBuffer();
		int index = 0;
		for (; index < selectedAmenities.size(); index++) {
			if (index == selectedAmenities.size() - 1)
				amenities.append(selectedAmenities.get(index).getName() + " .");
			else
				amenities
						.append(selectedAmenities.get(index).getName() + " , ");
		}

		return " " + amenities.toString();
	}

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
		return apartmentLocation;
	}

	public void setApartmentLocation(MapModel apartmentLocation) {
		this.apartmentLocation = apartmentLocation;
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

	/* | Initialize Components | */
	@PostConstruct
	public void init() {
		apartmentLocation = new DefaultMapModel();
		room = new Room();
		amenities = management.getAllAmenities();
		roles = management.getAllRoles();
		selectedAmenities = new ArrayList<Amenity>();
		selectedRoles = new ArrayList<Role>();
		initiateNewFacility();
	}

	/* | ON SUBMIT CLICK | */
	public void save() {
		// update map view
		try {
			facility.setIsDeleted(Boolean.FALSE);
			facility.setAmenities(selectedAmenities);
			facility.setRoles(selectedRoles);
			facility = management.addOrUpdateFacility(facility);
		} catch (RoomingException e1) {
			FacesMessage msg = new FacesMessage("Failure",
					"Sorry a problem occured while adding your facility ! ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		FacesMessage msg = new FacesMessage("Successful",
				"Your Facility Was Added Successfully ! ");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/* | ON DELETE CLICK | */
	public void deleteAction(Room room) {
		System.out.println(facility.getRooms().toString());
		facility.getRooms().remove(room);
		System.out.println(facility.getRooms().toString());
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
		if(index>-1){
			facility.getImages().remove(index);
		}
	}

	
	/* | ON NEXT CLICK | */
	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case facility goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	/* | Initialize Facility | */
	private void initiateNewFacility() {
		facility = new Facility();
		facility.setLan(30.070999);
		facility.setLon(31.021115);
		facility.setCountry("Egypt");
		facility.setDescription("");

		facility.setRoomAdvertiser(getCurrentlyLoggedRoomAdvertiser());
	}

	/* | Sets Marker on map from given address input | */
	public void changeOnMapFromAddress(AjaxBehaviorEvent event) {

		GeoLocationDetails address = MapUtil.getLocationFromAddress(facility);
		String fullAddress = setFacilityLocationDetails(address);
		LatLng latLng = new LatLng(facility.getLan(), facility.getLon());
		// Set Marker
		setNewMarker(latLng, fullAddress);
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
		apartmentLocation.getMarkers().clear();
		apartmentLocation.addOverlay(new Marker(latlng, fullAddress));
	}

	/* | Instantiate a new room | */
	public void newRoomInstance() {
		room = new Room();
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

	/* | Set Room Image | */
	public void uploadRoomImage(FileUploadEvent event) {
		try {
			Utils.copyFile(Configurations
					.getProperty(Configurations.UPLOADED_ROOM_IMAGES_PATH),
					event.getFile().getFileName(), event.getFile()
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
					.getProperty(Configurations.FACILITY_IMAGES_PATH),
					event.getFile().getFileName(), event.getFile()
							.getInputstream());

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

}
