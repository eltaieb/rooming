package com.iti.rooming.dataaccess.daoimpl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.iti.rooming.common.utils.Utils;

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

	public List load(Class cls, int first, int pageSize, String sortField,
			Boolean ascending, Map<String, Object> filters) {

		Query query = constructQuery(cls, first, pageSize, sortField,
				ascending, filters, false);
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		List<Object> result = query.getResultList();
		return result;

	}

	public Query constructQuery(Class cls, int first, int pageSize,
			String sortField, Boolean ascending, Map<String, Object> filters,
			Boolean isCount) {

		String filtersKey;
		Object filtersObject;
		Map<String, Object> paramValues = new HashMap();
		String alias = "x";

		String queryString;
		if (isCount) {
			queryString = "select count(o) from " + cls.getCanonicalName()
					+ " o where 1=1 ";
		} else {
			queryString = "select o from " + cls.getCanonicalName()
					+ " o where 1=1 ";
			if (sortField != null && ascending != null) {
				queryString += (ascending) ? " order by o." + sortField
						: " order by o." + sortField + " desc";
			}

		}

		Field field = null;
		Class c = null;
		if (filters.size() != 0) {
			for (Map.Entry<String, Object> entry : filters.entrySet()) {
				filtersKey = entry.getKey();
				filtersObject = entry.getValue();

				try {

					// System.out.println(filtersKey);
					String[] keys = filtersKey.split("\\.");

					for (int i = 0; i < keys.length; i++) {

						if ((keys.length - 1) == i) {
							if (c == null) {
								c = cls;
							}

							field = c.getDeclaredField(keys[i]);
						} else {
							c = cls.getDeclaredField(keys[i]).getType();
							field = c.getDeclaredField(keys[i + 1]);

						}
					}
					Class type = field.getType();

					if (type == String.class) {
						filtersObject = filtersObject.toString();
						// System.out.println("in string" + filtersObject);
						String currentAlias = alias += "x";
						paramValues.put(currentAlias, filtersObject);
						alias = currentAlias;
						queryString += " and o." + filtersKey + " like :"
								+ currentAlias;
					} else if (type == Date.class) {

						Date endOfDay = Utils.getEndOfDay((Date) filtersObject);
						Date startOfDay = Utils
								.getStartOfDay((Date) filtersObject);

						queryString += " and o." + filtersKey + " between "
								+ " ' " + startOfDay + " ' " + " and " + " ' "
								+ endOfDay + " ' ";
					} else if (Utils.isWrapperClass(type)) {

						// //////////////
						if (type == Long.class) {
							filtersObject = new Long(filtersObject.toString());
							String currentAlias = alias += "x";

							paramValues.put(currentAlias, filtersObject);
							alias = currentAlias;
							queryString += " and o." + filtersKey + " = :"
									+ currentAlias;
						} else if (type == Integer.class) {
							filtersObject = new Integer(
									filtersObject.toString());
							String currentAlias = alias += "x";

							paramValues.put(currentAlias, filtersObject);
							alias = currentAlias;
							queryString += " and o." + filtersKey + " = :"
									+ currentAlias;
						} else if (type == Float.class) {
							filtersObject = new Float(filtersObject.toString());

							String currentAlias = alias += "x";

							paramValues.put(currentAlias, filtersObject);
							alias = currentAlias;
							queryString += " and o." + filtersKey + " = :"
									+ currentAlias;
						} else if (type == Double.class) {
							filtersObject = new Double(filtersObject.toString());
							String currentAlias = alias += "x";

							paramValues.put(currentAlias, filtersObject);
							alias = currentAlias;
							queryString += " and o." + filtersKey + " = :"
									+ currentAlias;
						} else if (type == Short.class) {
							filtersObject = new Short(filtersObject.toString());
							String currentAlias = alias += "x";

							paramValues.put(currentAlias, filtersObject);
							alias = currentAlias;
							queryString += " and o." + filtersKey + " = :"
									+ currentAlias;
						}

					} else {
						// System.out.println("in else of filters "+
						// type.getCanonicalName());

						// System.out.println("in else2 of filters "+
						// filtersObject.getClass());

						String currentAlias = alias += "x";
						queryString += " and o." + filtersKey + " = :"
								+ currentAlias;
						paramValues.put(currentAlias, filtersObject);
						alias = currentAlias;

					}

				} catch (NoSuchFieldException e) {

					e.printStackTrace();
				} catch (SecurityException e) {

					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		Query query = em.createQuery(queryString);
		Set<Parameter<?>> parameters = query.getParameters();
		if (parameters.size() != 0) {
			for (Map.Entry<String, Object> entry : paramValues.entrySet())
				if (entry.getValue() instanceof String) {
					query.setParameter(entry.getKey(), "%" + entry.getValue()
							+ "%");
				} else {

					query.setParameter(entry.getKey(), entry.getValue());

				}

		}
		return query;
	}

	public int getNumOfRows(Class cls, Map<String, Object> filters) {

		Query query = em.createQuery("SELECt COUNT(x) FROM "
				+ cls.getCanonicalName() + " x ");
		int result = ((Long) query.getSingleResult()).intValue();
		return result;
	}
}
