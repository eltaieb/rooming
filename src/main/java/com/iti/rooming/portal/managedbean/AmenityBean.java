package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.gmap.GMap;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.Amenity;
import com.iti.rooming.common.exception.RoomingException;

@ManagedBean
@ViewScoped
public class AmenityBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private RoomingManagment management;
	private Amenity amenity;
	private LazyDataModel<Amenity> lazyFacilityModel;

	@PostConstruct
	public void init() {
		LoadData();
	}

	private void LoadData() {
		lazyFacilityModel = new LazyDataModel<Amenity>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<Amenity> result;

			@Override
			public Amenity getRowData(String rowKey) {
				for (Amenity amenity : result) {
					if (amenity.getId().toString().equals(rowKey))
						return amenity;
				}

				return null;
			}

			@Override
			public Object getRowKey(Amenity amenity) {
				return amenity.getId();
			}

			@Override
			public List<Amenity> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				result = management.loadAmenities(first, pageSize, sortField,
						sortOrder == SortOrder.ASCENDING, filters);
				this.setRowCount(management.getNumOfAdvertiserRows(filters));

				return result;
			}

			@Override
			public void forEach(Consumer<? super Amenity> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<Amenity> spliterator() {
				// TODO Auto-generated method stub
				return null;
			}

		};

	}

	public RoomingManagment getManagement() {
		return management;
	}

	public void setManagement(RoomingManagment management) {
		this.management = management;
	}

	public Amenity getAmenity() {
		return amenity;
	}

	public void setAmenity(Amenity amenity) {
		this.amenity = amenity;
	}

	public LazyDataModel<Amenity> getLazyFacilityModel() {
		return lazyFacilityModel;
	}

	public void setLazyFacilityModel(LazyDataModel<Amenity> lazyFacilityModel) {
		this.lazyFacilityModel = lazyFacilityModel;
	}

	public void insert() {

		try {
			management.saveOrUpdateAmenity(amenity);

		} catch (RoomingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void disablebuttons() {

		amenity = null;
	}

	public void initiateAmenity() {
		amenity = new Amenity();
	}

	public void update() {

		management.updateAmenity(amenity);
	}

}
