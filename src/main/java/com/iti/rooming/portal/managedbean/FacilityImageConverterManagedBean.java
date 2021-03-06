package com.iti.rooming.portal.managedbean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.entity.FacilityImage;
 
@ManagedBean
@ApplicationScoped
public class FacilityImageConverterManagedBean  implements Converter {
	
	@EJB
	RoomingManagment management;
	
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
            	Long id = new Long(value);
        		return management.getRole(id);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((FacilityImage) object).getId());
        }
        else {
            return null;
        }
    }   
} 