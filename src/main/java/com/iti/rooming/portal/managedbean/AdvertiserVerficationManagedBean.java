package com.iti.rooming.portal.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.config.Configurations;
import com.iti.rooming.common.entity.Category;
import com.iti.rooming.common.entity.Lookup;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.common.utils.Utils;

@ManagedBean
@ViewScoped
public class AdvertiserVerficationManagedBean extends BaseBean implements
		Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private RoomingManagment roomingManagment;
	private RoomAdvertiser roomAdvertiser;
	private List<Lookup> identificationTypes;
	private UploadedFile uploaded;
	private String uploadedFileName;
	private LazyDataModel<RoomAdvertiser> roomAdvertiserLazyModel;
	private boolean identificationDocuementUploadedSuccessfully;

	public boolean isIdentificationDocuementUploadedSuccessfully() {
		return identificationDocuementUploadedSuccessfully;
	}

	public void setIdentificationDocuementUploadedSuccessfully(
			boolean identificationDocuementUploadedSuccessfully) {
		this.identificationDocuementUploadedSuccessfully = identificationDocuementUploadedSuccessfully;
	}

	@PostConstruct
	public void init() {
		roomAdvertiser = getCurrentlyLoggedRoomAdvertiser();

		identificationTypes = roomingManagment
				.getLookupByCategory(Category.IDENTIFICATION_TYPE);
		initRoomAdvertiserLazyModel();

	}

	private void initRoomAdvertiserLazyModel() {

	}

	public RoomAdvertiser getRoomAdvertiser() {
		return roomAdvertiser;
	}

	public void setRoomAdvertiser(RoomAdvertiser roomAdvertiser) {
		this.roomAdvertiser = roomAdvertiser;
	}

	public UploadedFile getUploaded() {
		return uploaded;
	}

	public void setUploaded(UploadedFile uploaded) {
		this.uploaded = uploaded;
	}

	public String saveAdvisorVerfications() {

		try {
			if (identificationDocuementUploadedSuccessfully) {
				roomAdvertiser = roomingManagment
						.addOrUpdateRoomAdvertiser(roomAdvertiser);
				setCurrentLoggedRoomAdvertiser(roomAdvertiser);

				addInfoMessage("Verification document has been uploaded successfully");
				identificationDocuementUploadedSuccessfully = false;
			} else {
				addErrorMessage("Please upload identification document");
			}

		} catch (RoomingException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Lookup> getIdentificationTypes() {
		return identificationTypes;
	}

	public void setIdentificationTypes(List<Lookup> identificationTypes) {
		this.identificationTypes = identificationTypes;
	}

	public void upload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Success! ", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		try {
			Utils.copyFile(
					Configurations
							.getProperty(Configurations.UPLOAD_ADVERTISER_VERFICATION_DOCUMENTS_PATH),
					event.getFile().getFileName(), event.getFile()
							.getInputstream());
			identificationDocuementUploadedSuccessfully = true;
			roomAdvertiser.setIdentificationDocumentPath(event.getFile()
					.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
