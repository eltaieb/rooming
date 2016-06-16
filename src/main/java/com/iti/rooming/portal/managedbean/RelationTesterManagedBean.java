package com.iti.rooming.portal.managedbean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.RoomAdvertiser;

@ManagedBean
@ViewScoped
public class RelationTesterManagedBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private RoomingManagment roomingManagment;
	
	private Facility facility ;	


	public Facility getFacility() {
		return facility;
	}



	public void setFacility(Facility facility) {
		this.facility = facility;
	}



	@PostConstruct
	public void init() {
		RoomAdvertiser roomAdvertiser = getCurrentlyLoggedRoomAdvertiser();
		facility = roomingManagment.getFacility(27L);
		if(facility==null)
			return;
	}
}