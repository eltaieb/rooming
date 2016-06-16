package com.iti.rooming.business.service;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityRole;
import com.iti.rooming.common.entity.Role;
import com.iti.rooming.common.exception.RoomingException;

@Local
public interface FacilityRoleService {
	public FacilityRole saveOrUpdate (FacilityRole value) throws RoomingException;

	public List<Role> getRolesOfFacility(Facility facility);

	public void removeRoleByFacility(Facility facility) throws RoomingException; 

}
