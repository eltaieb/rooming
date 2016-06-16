package com.iti.rooming.dataaccess.dao;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityRole;
import com.iti.rooming.common.entity.Role;


@Local
public interface FacilityRoleDAO {
	public FacilityRole saveOrUpdate(FacilityRole facilityRole);

	public List<Role> getRoleByFacility(Facility facility);

	public void removeRoleByFacility(Facility facility);
}
