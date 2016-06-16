package com.iti.rooming.business.service;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.entity.Category;
import com.iti.rooming.common.entity.Lookup;

@Local
public interface LookupService {

	public List<Lookup> getLookupByCategory(Category category);

	public Lookup getLookupByID(Long id);
			
}
