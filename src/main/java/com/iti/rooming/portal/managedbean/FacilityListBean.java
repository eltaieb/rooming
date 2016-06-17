package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.Facility;
@ManagedBean
@ViewScoped
public class FacilityListBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	RoomingManagment management;

	private LazyDataModel<Facility> lazyFacilityModel;

	private Facility facility;


	




	@PostConstruct
	public void init() {
		LoadData();
	}
	
	private void LoadData() {
		lazyFacilityModel = new LazyDataModel<Facility>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<Facility> result;

			@Override
			public Facility getRowData(String rowKey) {
				for (Facility compound : result) {
					if (compound.getId().toString().equals(rowKey))
						return compound;
				}

				return null;
			}

			@Override
			public Object getRowKey(Facility compound) {
				return compound.getId();
			}

			@Override
			public List<Facility> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				result = management.loadFacility(first,
						pageSize, sortField, sortOrder == SortOrder.ASCENDING,
						filters);
				this.setRowCount(management
						.getNumOfAdvertiserRows(filters));

				return result;
			}

			@Override
			public void forEach(Consumer<? super Facility> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<Facility> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};

	}
	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public RoomingManagment getManagement() {
		return management;
	}

	public void setManagement(RoomingManagment management) {
		this.management = management;
	}

	public LazyDataModel<Facility> getLazyFacilityModel() {
		return lazyFacilityModel;
	}

	public void setLazyFacilityModel(LazyDataModel<Facility> lazyFacilityModel) {
		this.lazyFacilityModel = lazyFacilityModel;
	}
}
