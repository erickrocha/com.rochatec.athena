package com.rochatec.athena.pdv.service.init;

import java.math.BigDecimal;

import com.rochatec.athena.pdv.service.dao.IProductDao;
import com.rochatec.athena.pdv.service.dao.impl.ProductDaoImpl;
import com.rochatec.athena.pdv.service.model.Product;
import com.rochatec.mongo.MongoConnection;

public class Run {

	public static void main(String[] args){
		
		MongoConnection connection = MongoConnection.getInstance();
		IProductDao productDao = new ProductDaoImpl(connection);
		
		Product product = new Product();
		product.setId(1L);
		product.setName("CERVEJA HEINEKEN 355ML LN");
		product.setPrice(new BigDecimal(2.99));
		product.setStock(BigDecimal.TEN);
		product.setCategory("CERVEJAS");
		product.addBarcode("7891149000012");
		
		productDao.persist(product);
		
		for (Product p : productDao.findAll()){
			System.out.println(p.toString());
		}
		
	}
}
