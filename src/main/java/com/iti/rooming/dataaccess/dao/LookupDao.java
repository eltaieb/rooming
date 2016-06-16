package com.iti.rooming.dataaccess.dao;

import java.util.List;

import javax.ejb.Local;

import com.iti.rooming.common.entity.Category;
import com.iti.rooming.common.entity.Lookup;

@Local
public interface LookupDao {

	public List<Lookup> getLookupByCategory(Category category);
	public Lookup getLookupByID(Long id);
}
