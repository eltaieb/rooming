package com.iti.rooming.dataaccess.daoimpl;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.entity.Facility;
import com.iti.rooming.common.entity.Role;
import com.iti.rooming.dataaccess.dao.RoleDAO;

@Stateless
public class RoleDAOImpl extends BaseDAO implements RoleDAO {

	@Override
	public List<Role> getAll() {
		return super.getAll(Role.class);
	}

	@Override
	public Role find(Long id) {
		return (Role) super.find(Role.class, id);
	}

	@Override
	public Role saveOrUpdate(Role role) {
		return (Role) super.persist(role);
	}

	@Override
	public void remove(Role role) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Role> getRoleByFacility(Facility facility) {
		Query query = em
				.createQuery("SELECT r FROM FacilityRole r WHERE r.facility = :facility ");
		query = query.setParameter("facility", facility);
		return query.getResultList();
	}

	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		super.persist(role);
	}

	@Override
	public List<Role> loadRoles(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.load(Role.class, first, pageSize, sortField, ascending, filters);
	}

	@Override
	public int getNumOfRolesRows(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.getNumOfRows(Role.class, filters);
	}

}
