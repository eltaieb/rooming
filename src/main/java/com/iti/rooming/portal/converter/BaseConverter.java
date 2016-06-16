package com.iti.rooming.portal.converter;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.iti.rooming.business.management.RoomingManagment;
import com.iti.rooming.common.utils.Utils;

@ManagedBean
@ApplicationScoped
public class BaseConverter {
	@EJB
	protected RoomingManagment roomingManagment;

	public boolean isLongNumber(String string) {
		try {
			Long.parseLong(string);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isEmpty(String string) {
		return Utils.isNull(string) || string.trim().length() == 0;
	}

}
