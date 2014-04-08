package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.rochatec.athena.eao.local.CategoryEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Category;

@Stateless
public class CategoryEaoImpl extends GenericEao<Category, Serializable>
		implements CategoryEaoLocal {

	public Category findById(Long id) {
		Category category = super.findById(id);
		return category;
	}

	@SuppressWarnings("unchecked")
	public List<Category> findByName(String name) {
		Query query = getEntityManager()
				.createNamedQuery("Category.findByName");
		query.setParameter("name", name);
		List<Category> categories = query.getResultList();
		return categories;
	}

	@SuppressWarnings("unchecked")
	public List<Category> findAll() {
		Query query = getEntityManager().createNamedQuery("Category.findAll");
		List<Category> categories = query.getResultList();
		return categories;
	}

}
