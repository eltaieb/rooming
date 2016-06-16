package com.iti.rooming.business.service;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Role;
import com.iti.rooming.common.exception.RoomingException;

@Local
public interface RoleService {

	public List<Role> getAll ();
	public Role find(Long id);
	public Role saveOrUpdate(Role role) throws RoomingException;
	public void remove(Role role) throws RoomingException;
	public List<Role> getRoleByFacility(Facility facility);
}
