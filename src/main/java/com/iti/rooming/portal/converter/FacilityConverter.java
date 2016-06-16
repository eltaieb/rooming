package com.iti.rooming.portal.converter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.utils.Utils;

@ManagedBean
@ApplicationScoped
public class FacilityConverter extends BaseConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String str) {

		if (Utils.isNotEmpty(str) && Utils.isNumericValue(str)) {
			Facility facility;
			try {
				facility =roomingManagment.getFacility(new Long(str));
				return facility;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object != null) {
			if (object instanceof Facility) {
				Long id = ((Facility) object).getId();
				return id + "";
			} else {
				throw new IllegalStateException(
						"object is not instaoce of MenuItem ");
			}
		}
		return "";
	}

}
