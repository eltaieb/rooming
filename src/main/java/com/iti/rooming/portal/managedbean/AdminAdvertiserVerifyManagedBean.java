package com.iti.rooming.portal.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.Amenity;
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

	private LazyDataModel<RoomAdvertiser> model;
	private RoomAdvertiser selectedRoomAdvertiser;

	@PostConstruct
	public void init() {
		selectedRoomAdvertiser = new RoomAdvertiser();
		roomAdvertiser = new RoomAdvertiser();
		loadInLazyModel();

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
				advertisers = roomingManagment.findAllUnValidateAdvisors(first,
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

	public void verify(RoomAdvertiser roomAdvertis) {
		roomAdvertis.setIsVerified(true);
		try {
			roomingManagment.addOrUpdateRoomAdvertiser(roomAdvertis);
			loadInLazyModel();
		} catch (RoomingException e) {
			e.printStackTrace();
		}
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

}
