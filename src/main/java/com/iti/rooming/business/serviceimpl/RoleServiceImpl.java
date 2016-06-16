package com.iti.rooming.business.serviceimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.iti.rooming.business.service.RoleService;
import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Role;
import com.iti.rooming.common.exception.RoomingException;
import com.iti.rooming.dataaccess.dao.RoleDAO;

@Stateless
public class RoleServiceImpl implements RoleService  {

	@EJB
	RoleDAO roleDAO ;
	
	@Override
	public List<Role> getAll() {
		return roleDAO.getAll();
	}

	@Override
	public Role find(Long id) {
		return roleDAO.find(id);
	}

	@Override
	public Role saveOrUpdate(Role role) throws RoomingException {
		return roleDAO.saveOrUpdate(role);
	}

	@Override
	public void remove(Role role) throws RoomingException {
		// TODO Auto-generated method stub		
	}

	@Override
	public List<Role> getRoleByFacility(Facility facility) {
		return roleDAO.getRoleByFacility(facility);

	}

}
