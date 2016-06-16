package com.iti.rooming.dataaccess.dao;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Role;

@Local
public interface RoleDAO  {
	public List<Role> getAll ();
	public void remove(Role role);
	public Role find(Long id);
	public Role saveOrUpdate(Role role);
	public List<Role> getRoleByFacility(Facility facility);
}
