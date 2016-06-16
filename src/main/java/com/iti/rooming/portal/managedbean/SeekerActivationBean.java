package com.iti.rooming.portal.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.utils.FacesContextManager;

@ManagedBean
@ViewScoped
public class SeekerActivationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private RoomingManagment roomingManagment;
	private RoomSeeker roomSeeker;
	private String activationToken;

	@PostConstruct
	private void init() {
		activationToken = FacesContextManager.getParameter("token");
		roomSeeker = new RoomSeeker();
		roomSeeker.setActivationToken(activationToken);
		roomSeeker = roomingManagment.activeSeekerAccount(roomSeeker);
		if (roomSeeker == null) {
			FacesContextManager.addMessage("invalid url");
			return;
		}
		FacesContextManager.addMessage("account is activated");

	}

	public RoomSeeker getRoomSeeker() {
		return roomSeeker;
	}

	public void setRoomSeeker(RoomSeeker roomSeeker) {
		this.roomSeeker = roomSeeker;
	}

}
