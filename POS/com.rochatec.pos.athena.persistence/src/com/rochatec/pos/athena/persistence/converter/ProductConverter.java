package com.rochatec.pos.athena.persistence.converter;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.DBObject;
import com.rochatec.mongo.converter.IEntityConverter;
import com.rochatec.mongo.json.JSONApi;
import com.rochatec.mongo.json.JSONApiBuilder;
import com.rochatec.pos.athena.persistence.model.Product;

public class ProductConverter implements IEntityConverter<Product>{

	@Override
	public Product toEntity(Map<String, Object> params) {
		JSONApi jsonApi = new JSONApiBuilder().build();
		String value =  jsonApi.serialize(params);
		return (Product) jsonApi.deserialize(value, Product.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Product toEntity(DBObject dbObject) {
		return toEntity(dbObject.toMap());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> toMap(Product entity) {
		JSONApi jsonApi = new JSONApiBuilder().build();
		String value =  jsonApi.serialize(entity);
		return (HashMap) jsonApi.deserialize(value, HashMap.class);
	}

}
