package com.rochatec.pos.athena.persistence.repository.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.rochatec.pos.athena.persistence.model.BarCode;
import com.rochatec.pos.athena.persistence.model.Product;
import com.rochatec.pos.athena.persistence.repository.IProductRepository;

@Repository
public class ProductRepositoryImpl extends GenericRepository<Product,Serializable> implements IProductRepository {

	public static final String ID = "com.rochatec.pos.athena.persistence.repository.impl.ProductRepositoryImpl";
	
	@Override
	public Product findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Product findByBarcode(BarCode barCode) {
		return findByBarcode(barCode.getBarcode());
	}

	@Override
	public Product findByBarcode(String barcode) {
		String hql = "SELECT p FROM Product p LEFT JOIN FETCH p.barCodes b WHERE b.barcode = :barcode ";
		Query query = getEntityManager().createQuery(hql,Product.class);
		query.setParameter("barcode",barcode);
		Product product = (Product)query.getSingleResult();
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Product> findByName(String name) {
		String hql = "SELECT p FROM Product p LEFT JOIN FETCH p.barCodes WHERE p.name like :name OR p.shortName like :shortName ";
		Query query = getEntityManager().createQuery(hql,Product.class);
		query.setParameter("name",name);
		query.setParameter("shortName", name);
		Set<Product> products = new HashSet<Product>(query.getResultList());
		return products;
	}

}
