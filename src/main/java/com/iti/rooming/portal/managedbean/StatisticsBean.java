package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.dto.FacilityCity;
import com.iti.rooming.common.dto.RoomAdvertiserCity;
import com.iti.rooming.common.dto.RoomingChatMessage;
import com.iti.rooming.common.entity.RoomAdvertiser;

@ManagedBean
@ViewScoped
public class StatisticsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private RoomingManagment roomingManagment;
	private PieChartModel facilityPieModel;
	private PieChartModel roomAdvertiserPieModel;
	private List<RoomAdvertiserCity> roomAdvertiserCities;
	private List<FacilityCity> facilityCities;

	@PostConstruct
	public void init() {
		facilityPieModel = new PieChartModel();
		roomAdvertiserPieModel = new PieChartModel();
		loadFacilitChartModel();
		loadRoomAdvertiserPieModel();
	}

	private void loadFacilitChartModel() {
		facilityCities = roomingManagment.getFacilitiesInCities();
		facilityCities.parallelStream()
				.forEach(
						fc -> facilityPieModel.set(fc.getCity(),
								fc.getnOfFacilities()));

		facilityPieModel.setTitle("Facilities Statistics");
		facilityPieModel.setLegendPosition("e");
		facilityPieModel.setExtender("pieExtender");

	}

	private void loadRoomAdvertiserPieModel() {
		roomAdvertiserCities = roomingManagment.getRoomAdvertiserInCities();
		roomAdvertiserCities.parallelStream().forEach(
				rc -> roomAdvertiserPieModel.set(rc.getCity(),
						rc.getnOfRoomAdvertisr()));
		roomAdvertiserPieModel.setTitle("Room Advertiser Statistics");
		roomAdvertiserPieModel.setLegendPosition("e");
		roomAdvertiserPieModel.setExtender("pieExtender");

	}

	public PieChartModel getFacilityPieModel() {
		return facilityPieModel;
	}

	public void setFacilityPieModel(PieChartModel facilityPieModel) {
		this.facilityPieModel = facilityPieModel;
	}

	public PieChartModel getRoomAdvertiserPieModel() {
		return roomAdvertiserPieModel;
	}

	public void setRoomAdvertiserPieModel(PieChartModel roomAdvertiserPieModel) {
		this.roomAdvertiserPieModel = roomAdvertiserPieModel;
	}

}
