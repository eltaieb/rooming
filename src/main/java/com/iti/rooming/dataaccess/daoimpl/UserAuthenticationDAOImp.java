package com.iti.rooming.dataaccess.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.iti.rooming.common.entity.Admin;
import com.iti.rooming.common.entity.RoomAdvertiser;
import com.iti.rooming.common.entity.RoomSeeker;
import com.iti.rooming.common.entity.User;
import com.iti.rooming.common.utils.Utils;
import com.iti.rooming.dataaccess.dao.UserAuthenticationDAO;

@Stateless
public class UserAuthenticationDAOImp extends BaseDAO implements
		UserAuthenticationDAO {

	public User LoginAuthentication(String username) {

		TypedQuery<Object[]> query = em
				.createQuery(
						"SELECT c.id, c.role FROM User AS c  WHERE c.username = :username",
						Object[].class);
		query.setParameter("username", username);
		
		List<Object[]> results = query.getResultList();

		Long id     = null;
		Object obj  = null;
		String role = null;
		Query q     = null;

		if (Utils.isNotEmpty(results)) {

			id = (Long) results.get(0)[0];
			role = (String) results.get(0)[1];

			if (role.equals("admin")) {

				q = em.createQuery("select a from Admin a where a.id =:id");

			} else if (role.equals("seeker")) {
				
				obj = null;
				//q = em.createQuery("select s from RoomSeeker s where s.id =:id");

			} else if (role.equals("advertiser")) {

				q = em.createQuery("select o from RoomAdvertiser o where o.id =:id");

			}

			q.setParameter("id", id);
			obj = q.getSingleResult();
		}
		
		return (User) obj;
		
	}

}
