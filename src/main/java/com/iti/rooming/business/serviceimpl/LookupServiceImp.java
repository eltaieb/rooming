package com.iti.rooming.business.serviceimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.iti.rooming.business.service.LookupService;
import com.iti.rooming.common.entity.Category;
import com.iti.rooming.common.entity.Lookup;
import com.iti.rooming.dataaccess.dao.LookupDao;

@Stateless
public class LookupServiceImp implements LookupService {

	@EJB
	private LookupDao lookupDao;

	@Override
	public List<Lookup> getLookupByCategory(Category category) {
		return lookupDao.getLookupByCategory(category);
	}

	@Override
	public Lookup getLookupByID(Long id) {
		return lookupDao.getLookupByID(id);
	}

}
