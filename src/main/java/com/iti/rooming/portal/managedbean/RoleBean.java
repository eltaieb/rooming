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
import com.iti.rooming.common.entity.Role;
import com.iti.rooming.common.exception.RoomingException;

@ManagedBean
@ViewScoped
public class RoleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private RoomingManagment management;
	private Role role;
	private LazyDataModel<Role> lazyRoleModel;

	@PostConstruct
	public void init() {
		LoadData();
	}

	private void LoadData() {
		lazyRoleModel = new LazyDataModel<Role>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private List<Role> result;

			@Override
			public Role getRowData(String rowKey) {
				for (Role role : result) {
					if (role.getId().toString().equals(rowKey))
						return role;
				}

				return null;
			}

			@Override
			public Object getRowKey(Role role) {
				return role.getId();
			}

			@Override
			public List<Role> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				result = management.loadRoles(first, pageSize, sortField,
						sortOrder == SortOrder.ASCENDING, filters);
				this.setRowCount(management.getNumOfAdvertiserRows(filters));

				return result;
			}

			@Override
			public void forEach(Consumer<? super Role> action) {
				// TODO Auto-generated method stub

			}

			@Override
			public Spliterator<Role> spliterator() {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LazyDataModel<Role> getLazyRoleModel() {
		return lazyRoleModel;
	}

	public void setLazyRoleModel(LazyDataModel<Role> lazyRoleModel) {
		this.lazyRoleModel = lazyRoleModel;
	}

	public void insert() {
		management.updateRole(role);
	}

	public void disablebuttons() {

		role = null;
	}

	public void initiateRole() {
		role = new Role();
	}

	public void update() {

		management.updateRole(role);
	}

}
