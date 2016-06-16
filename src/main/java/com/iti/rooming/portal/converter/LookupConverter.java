package com.iti.rooming.portal.converter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.iti.rooming.common.entity.Lookup;

@ManagedBean
@ApplicationScoped
public class LookupConverter extends BaseConverter implements Converter {

	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		// get value
		// value not null && number.(long)
		// return object by id
		if (!isEmpty(value) && isLongNumber(value)) {
			return roomingManagment.getLookupByID(new Long(value));
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		// cast to lookup
		// get id
		// convert to string

		Lookup lookup = (Lookup) obj;
		return lookup.getId().toString();

	}


}
