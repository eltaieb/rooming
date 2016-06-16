package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.utils.Bounds;

@ManagedBean
@ViewScoped
public class TestBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private RoomingManagment pinAppManagement;

	
	private Facility facility ;	


	public Facility getFacility() {
		return facility;
	}



	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	@PostConstruct
	public void init() {
		BigDecimal minimumPrice = new BigDecimal("0.0");
		BigDecimal maximumPrice = new BigDecimal("900.0");
		Double minimumLatitude = 3.3;
		Double minimumLongitude = 3.3;
		Bounds b = new Bounds(30.0 , 32.0  , 30.0 , 32.0);

		
		List<Facility> facilities = pinAppManagement
				.getFacilitiesByBoundariesAndPrice(minimumPrice, maximumPrice,
						b);
		RoomAdvertiser roomAdvertiser = getCurrentlyLoggedRoomAdvertiser();
		facility = pinAppManagement.getFacility(27L);
		if(facility==null)
			return;
		return;
	}

}
