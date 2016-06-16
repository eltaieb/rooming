package com.iti.rooming.dataaccess.daoimpl;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iti.rooming.common.exception.RoomingException;

@Stateless
public class BaseDAO {

	@PersistenceContext
	protected EntityManager em;

	public Object persist(Object obj) {
		return em.merge(obj);
	}

	public void saveOrUpdate(Object obj) {
		em.persist(obj);
	}

	public void remove(Object obj) {
		em.remove(obj);
	}

	public <T> List<T> getAll(Class<T> className) {
		Query query = em.createQuery("from " + className.getCanonicalName());
		List<T> result = (List<T>) query.getResultList();
		return result;
	}

	public <T> Object find(Class clazz, Long id) {
		return (T) em.find(clazz, id);
	}

	public List load(Class clazz, int first, int pageSize, String sortField,
			Boolean ascending, Map<String, Object> filters) {
		Query query;

		String queryString = "SELECObject x FROM " + clazz + " x ";

		if (sortField != null && ascending != null) {
			queryString += (ascending) ? " ORDER BY x." + sortField
					: " ORDER BY x." + sortField + " DESC";
		}

		query = em.createQuery(queryString);
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		List result = query.getResultList();
		return result;
	}

	public int getNumOfRows(Class cls, Map<String, Object> filters) {

		Query query = em.createQuery("SELECt COUNT(x) FROM "
				+ cls.getCanonicalName() + " x ");
		int result = ((Long) query.getSingleResult()).intValue();
		return result;
	}
}
