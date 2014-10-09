package com.rochatec.athena.pdv.service.converter;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.DBObject;
import com.rochatec.athena.pdv.service.model.Product;
import com.rochatec.mongo.converter.IEntityConverter;
import com.rochatec.mongo.json.JSONApi;

public class ProductConverter implements IEntityConverter<Product>{

	@Override
	public Product toEntity(Map<String, Object> map) {
		JSONApi jsonApi = JSONApi.defaultBuilder().build();
		String string = jsonApi.serialize(map);
		return (Product) jsonApi.deserialize(string, Product.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Product toEntity(DBObject dbObject) {		
		return toEntity(dbObject.toMap());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> toMap(Product product) {
		JSONApi jsonApi = JSONApi.defaultBuilder().build();
		String string = jsonApi.serialize(product);
		return (Map<String, Object>) jsonApi.deserialize(string,HashMap.class);
	}

}
