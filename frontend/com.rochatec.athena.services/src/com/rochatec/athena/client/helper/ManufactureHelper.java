package com.rochatec.athena.client.helper;

import java.util.List;

import com.rochatec.athena.model.ProductSet;
import com.rochatec.athena.model.ProductSetItem;


public class ManufactureHelper {
	
	public void fill(ProductSet productSet,List<ProductSetItem> productSetItems){
		if (productSetItems != null && !productSetItems.isEmpty()){
			for (ProductSetItem set : productSetItems){
				set.setParent(productSet);
			}
			productSet.setListChildren(productSetItems);
		}
	}
}
