package com.iti.rooming.dataaccess.daoimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.iti.rooming.common.entity.Category;
import com.iti.rooming.common.entity.Lookup;
import com.iti.rooming.dataaccess.dao.LookupDao;

@Stateless
public class LookupImp extends BaseDAO implements LookupDao {

	@Override
	public List<Lookup> getLookupByCategory(Category category) {

		Query query = em
				.createQuery("select l from Lookup l WHERE l.category = :category ");
		query.setParameter("category", category);
		return query.getResultList();
	}

	@Override
	public Lookup getLookupByID(Long id) {
		return (Lookup) super.find(Lookup.class, id);
	}

}
