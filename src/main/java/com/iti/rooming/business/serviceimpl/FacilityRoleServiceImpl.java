package com.iti.rooming.business.serviceimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.iti.rooming.business.service.FacilityRoleService;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityRole;
import com.iti.rooming.common.entity.Role;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.dataaccess.dao.FacilityRoleDAO;

@Stateless
public class FacilityRoleServiceImpl implements FacilityRoleService{

	@EJB
	FacilityRoleDAO facilityRoleDAO;
	@Override
	public FacilityRole saveOrUpdate(FacilityRole value) throws RoomingException {
		return facilityRoleDAO.saveOrUpdate(value);
	}
	@Override
	public List<Role> getRolesOfFacility(Facility facility) {
		return facilityRoleDAO.getRoleByFacility(facility);
	}
	@Override
	public void removeRoleByFacility(Facility facility) throws RoomingException {
		facilityRoleDAO.removeRoleByFacility(facility);
	}

}
