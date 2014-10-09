package com.rochatec.athena.pdv.service.dao;

import com.rochatec.athena.pdv.service.model.Product;
import com.rochatec.mongo.dao.IEntityDao;

public interface IProductDao extends IEntityDao<Product>{
	
	public Product findByKey(String key);
	
	public Product findByBarcode(String barcode);

}
