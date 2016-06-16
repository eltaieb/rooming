package com.iti.rooming.portal.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.config.Configurations;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.Utils;

@ManagedBean
@ViewScoped
public class RoomAdvertiserManagedBean extends BaseBean implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private RoomAdvertiser roomAdvertiser;

	private UploadedFile uploaded;

	public RoomAdvertiserManagedBean() {

		roomAdvertiser = new RoomAdvertiser();
		roomAdvertiser.setRole("advertiser");

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

	@EJB
	private RoomingManagment pinAppManagement;

	// private List<RoomAdvertiser> advisrors;

	public String saveRoomAdvisor() {

		try {

			roomAdvertiser.setProfileImage(uploaded.getFileName());
			Utils.copyFile(Configurations
					.getProperty(Configurations.UPLOAD_PROFILE_IMAGES_PATH),
					uploaded.getFileName(), uploaded.getInputstream());
			pinAppManagement.addOrUpdateRoomAdvertiser(roomAdvertiser);

			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/rooming/usr/home.xhtml");
		} catch (RoomingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<RoomAdvertiser> findAllRoomAdvisors() {

		List<RoomAdvertiser> advisrors = new ArrayList<RoomAdvertiser>();
		try {
			advisrors = pinAppManagement.findRoomAdvertisers();
		} catch (RoomingException e) {
			e.printStackTrace();
		}

		return advisrors;

	}

}
