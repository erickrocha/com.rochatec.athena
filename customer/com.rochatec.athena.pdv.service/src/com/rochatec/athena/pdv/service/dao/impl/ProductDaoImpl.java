package com.rochatec.athena.pdv.service.dao.impl;

import com.rochatec.athena.pdv.service.converter.ProductConverter;
import com.rochatec.athena.pdv.service.dao.IProductDao;
import com.rochatec.athena.pdv.service.model.Product;
import com.rochatec.mongo.MongoConnection;
import com.rochatec.mongo.converter.IEntityConverter;
import com.rochatec.mongo.dao.impl.EntityDao;

public class ProductDaoImpl extends EntityDao<Product> implements IProductDao{

	
	
	public ProductDaoImpl(MongoConnection client) {
		super(client);
	}

	@Override
	public IEntityConverter<Product> getConverter() {
		return new ProductConverter();
	}

	@Override
	public Product findByKey(String key) {
		return null;
	}

	@Override
	public Product findByBarcode(String barcode) {
		return null;
	}

	

}
