package com.rochatec.pos.athena.persistence.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.rochatec.mongo.MongoConnection;
import com.rochatec.mongo.converter.IEntityConverter;
import com.rochatec.mongo.dao.impl.EntityDao;
import com.rochatec.pos.athena.persistence.converter.ProductConverter;
import com.rochatec.pos.athena.persistence.dao.ProductDao;
import com.rochatec.pos.athena.persistence.model.Product;

public class ProductDaoImpl extends EntityDao<Product> implements ProductDao{

	public ProductDaoImpl(MongoConnection client) {
		super(client);
	}

	@Override
	public IEntityConverter<Product> getConverter() {		
		return new ProductConverter();
	}

	@Override
	public Product findById(Long id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id",id);
		return super.findOne(params);
	}

}
