package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.RoomAdvertiser;

@ManagedBean
@ViewScoped
public class RoomAdvertiserListBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private RoomingManagment roomingManagment;
	private LazyDataModel<RoomAdvertiser> model;
	private RoomAdvertiser selectedRoomAdvertiser;
	private RoomAdvertiser roomadvertiser;
	private List<Facility> facilities;
	private List<RoomAdvertiser> roomAdvertisers;
	private Facility selectedFacility;
	private MapModel mapModel;

	@PostConstruct
	public void init() {
		mapModel = new DefaultMapModel();
		selectedRoomAdvertiser = new RoomAdvertiser();
		selectedFacility = new Facility();
		roomadvertiser = new RoomAdvertiser();
		roomAdvertisers = roomingManagment.getAllRoomAdvertisers();
		// loadInLazyModel();
	}

	private void loadInLazyModel() {

		model = new LazyDataModel<RoomAdvertiser>() {
			private static final long serialVersionUID = 1L;
			private List<RoomAdvertiser> advertisers;

			@Override
			public RoomAdvertiser getRowData(String rowKey) {
				for (RoomAdvertiser advertiser : advertisers) {
					if (advertiser.getId().toString().equals(rowKey))
						return advertiser;
				}

				return null;
			}

			@Override
			public Object getRowKey(RoomAdvertiser order) {
				return order.getId();
			}

			public List<RoomAdvertiser> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				advertisers = roomingManagment.loadAdvertisersLazyMode(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(roomingManagment
						.getNumOfAdvertiserRows(filters));

				return advertisers;

			}

			@Override
			public void forEach(Consumer<? super RoomAdvertiser> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<RoomAdvertiser> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};

	}

	public RoomingManagment getRoomingManagment() {
		return roomingManagment;
	}

	public void setRoomingManagment(RoomingManagment roomingManagment) {
		this.roomingManagment = roomingManagment;
	}

	public LazyDataModel<RoomAdvertiser> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<RoomAdvertiser> model) {
		this.model = model;
	}

	public RoomAdvertiser getSelectedRoomAdvertiser() {
		return selectedRoomAdvertiser;
	}

	public void setSelectedRoomAdvertiser(RoomAdvertiser selectedRoomAdvertiser) {
		this.selectedRoomAdvertiser = selectedRoomAdvertiser;
	}

	public RoomAdvertiser getRoomadvertiser() {
		return roomadvertiser;
	}

	public void setRoomadvertiser(RoomAdvertiser roomadvertiser) {
		this.roomadvertiser = roomadvertiser;
	}

	public List<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(List<Facility> facilities) {
		this.facilities = facilities;
	}

	public void onRowToggle(ToggleEvent event) {
		RoomAdvertiser roomAdvertiser = (RoomAdvertiser) event.getData();
		facilities = roomingManagment.getAllFacilitiesOf(roomAdvertiser);

	}

	public List<RoomAdvertiser> getRoomAdvertisers() {
		return roomAdvertisers;
	}

	public void setRoomAdvertisers(List<RoomAdvertiser> roomAdvertisers) {
		this.roomAdvertisers = roomAdvertisers;
	}

	public Facility getSelectedFacility() {
		return selectedFacility;
	}

	public void setSelectedFacility(Facility selectedFacility) {
		this.selectedFacility = selectedFacility;
	}

	public void addFacilityToPin(Facility facility) {
		setSelectedFacility(facility);
		LatLng coord1 = new LatLng(facility.getLan(), facility.getLon());
		mapModel.getMarkers().clear();
		mapModel.addOverlay(new Marker(coord1));
		System.out.println("executed");
	}

	public MapModel getMapModel() {
		return mapModel;
	}
}
