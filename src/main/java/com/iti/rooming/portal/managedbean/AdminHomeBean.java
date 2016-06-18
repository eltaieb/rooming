package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.dto.OnlineUsers;

@ManagedBean
@ViewScoped
public class AdminHomeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private RoomingManagment roomingManagment;
	private DonutChartModel donutModel;
	private Long nOfOfflineUsers;

	@PostConstruct
	public void init() {
		loadAreaModel();
	}

	private void loadAreaModel() {
		donutModel = new DonutChartModel();

		nOfOfflineUsers = roomingManagment.getNofOfflineUsers();

		Map<String, Number> usersCircle = new LinkedHashMap<>();
		usersCircle.put("online users", OnlineUsers.getNOfOnlineUsers());
		usersCircle.put("offline users", nOfOfflineUsers);
		donutModel.addCircle(usersCircle);

		
		donutModel.setTitle("Users");
		donutModel.setLegendPosition("w");

	}

	public DonutChartModel getDonutModel() {
		return donutModel;
	}

	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}

}
