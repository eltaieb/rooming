package com.iti.rooming.portal.converter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.utils.Utils;

@ManagedBean
@ApplicationScoped
@FacesConverter("seekerConverter")
public class SeekerConverter extends BaseConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String str) {

		if (Utils.isNotEmpty(str) && Utils.isNumericValue(str)) {
			RoomSeeker roomSeeker;
			try {
				roomSeeker = roomingManagment.getSeekerById(new Long(str));
				return roomSeeker;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null) {
			if (object instanceof RoomSeeker) {
				Long id = ((RoomSeeker) object).getId();
				return id + "";
			} else {
				throw new IllegalStateException(
						"object is not instace of RoomSeeker ");
			}
		}
		return "";
	}

}
