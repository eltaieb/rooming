package com.iti.rooming.dataaccess.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.FacilityRole;
import com.iti.rooming.common.entity.Role;
import com.iti.rooming.dataaccess.dao.FacilityRoleDAO;

@Stateless
public class FacilityRoleDAOImpl extends BaseDAO implements FacilityRoleDAO{

	@Override
	public FacilityRole saveOrUpdate(FacilityRole facilityRole) {
		return (FacilityRole)super.persist(facilityRole);
	}


	@Override
	public List<Role> getRoleByFacility(Facility facility) {
		Query q = em
				.createQuery("SELECT DISTINCT fr.role FROM FacilityRole fr WHERE fr.facility = :facility");
		q.setParameter("facility", facility);
		return (List<Role>) q.getResultList();
	}


	@Override
	public void removeRoleByFacility(Facility facility) {
		if (facility != null) {
			Query query = em
					.createQuery("DELETE FROM FacilityRole fr WHERE fr.facility = :facility");
			query.setParameter("facility", facility).executeUpdate();
		}
	}

}
