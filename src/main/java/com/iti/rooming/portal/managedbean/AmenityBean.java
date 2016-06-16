package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.Amenity;

@ManagedBean
@ViewScoped
public class AmenityBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private RoomingManagment roomingManagment;
	private List<Amenity> amenities;
	private Amenity selectedAmenity;
	private Amenity Amenity;

	@PostConstruct
	public void init() {

	}

	//
	// private void initLazyModel() {
	// amenityLazyModel = new LazyDataModel<Amenity>() {
	// private static final long serialVersionUID = 1L;
	// private List<Amenity> amenities;
	//
	// @Override
	// public Amenity getRowData(String rowKey) {
	// for (Amenity amenity : amenities) {
	// if (Amenity.getId().toString().equals(rowKey))
	// return Amenity;
	// }
	//
	// return null;
	// }
	//
	// @Override
	// public Object getRowKey(Amenity order) {
	// return order.getId();
	// }
	//
	// public List<Amenity> load(int first, int pageSize,
	// String sortField, SortOrder sortOrder,
	// Map<String, Object> filters) {
	//
	// amenities = roomingManagment.loadAmenities(first, pageSize,
	// sortField, sortOrder == SortOrder.ASCENDING, filters);
	// this.setRowCount(roomingManagment.getNumOfAmenityRows(filters));
	// System.out.println(amenities);
	// return amenities;
	//
	// }
	//
	// };
	// }

	public Amenity getSelectedAmenity() {
		return selectedAmenity;
	}

	public void setSelectedAmenity(Amenity selectedAmenity) {
		this.selectedAmenity = selectedAmenity;
	}

	public Amenity getAmenity() {
		return Amenity;
	}

	public void setAmenity(Amenity Amenity) {
		this.Amenity = Amenity;
	}

}
