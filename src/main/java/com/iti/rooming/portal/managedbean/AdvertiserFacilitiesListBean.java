package com.iti.rooming.portal.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.Facility;

@ManagedBean
@ViewScoped
public class AdvertiserFacilitiesListBean extends BaseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private RoomingManagment roomingManagment;
	
	private List<Facility> facilities;
	
	private Facility currentFacility;


	public Facility getCurrentFacility() {
		return currentFacility;
	}

	public void setCurrentFacility(Facility currentFacility) {
		this.currentFacility = currentFacility;
	}

	@PostConstruct
	public void init()
	{
		facilities = roomingManagment.getAllFacilities(getCurrentlyLoggedRoomAdvertiser());
	}
	
	
	public void viewFacility(Facility facility) throws IOException
	{
		redirect("/rooming/usr/facilityview.xhtml?facilityId="+facility.getId());
	}
	
	public void editFacility(Facility facility) throws IOException
	{
		redirect("/rooming/usr/facilityedit.xhtml?facilityId="+facility.getId());

	}
	
	
	public void assignCurrentlySelectedFacility(Facility facility)
	{
		currentFacility = facility;
		
	}
	
	public List<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(List<Facility> facilities) {
		this.facilities = facilities;
	}
}
