package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.UploadedFile;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.exception.RoomingException;

@ManagedBean
@ViewScoped
public class AdminAdvertiserVerifyManagedBean extends BaseBean implements
		Serializable {

	@EJB
	private RoomingManagment roomingManagment;

	private static final long serialVersionUID = 1L;

	private RoomAdvertiser roomAdvertiser;

	private UploadedFile uploaded;
	
	List<RoomAdvertiser> unValidateAdvisrors;

	@PostConstruct
	public void init() {
		roomAdvertiser = new RoomAdvertiser();
		unValidateAdvisrors = findAllUnValidateAdvisors();

	}

	public UploadedFile getUploaded() {
		return uploaded;
	}

	public void setUploaded(UploadedFile uploaded) {
		this.uploaded = uploaded;
	}

	public RoomAdvertiser getRoomAdvertiser() {
		return roomAdvertiser;
	}

	public void setRoomAdvertiser(RoomAdvertiser roomAdvertiser) {
		this.roomAdvertiser = roomAdvertiser;
	}

	public List<RoomAdvertiser> findAllUnValidateAdvisors() {

		unValidateAdvisrors = roomingManagment.findUnValidAdvertisers();

		return unValidateAdvisrors;

	}

	public void verify(RoomAdvertiser roomAdvertis) {
		roomAdvertis.setIsVerified(true);
		try {
			roomingManagment.addOrUpdateRoomAdvertiser(roomAdvertis);
			findAllUnValidateAdvisors();
		} catch (RoomingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<RoomAdvertiser> getUnValidateAdvisrors() {
		return unValidateAdvisrors;
	}

	public void setUnValidateAdvisrors(List<RoomAdvertiser> unValidateAdvisrors) {
		this.unValidateAdvisrors = unValidateAdvisrors;
	}
}
