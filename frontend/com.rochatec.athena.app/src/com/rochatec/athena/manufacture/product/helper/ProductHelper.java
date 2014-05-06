package com.rochatec.athena.manufacture.product.helper;

import java.util.List;

import com.rochatec.athena.model.BarCode;
import com.rochatec.athena.model.Product;

public class ProductHelper {

	private Product product;
	
	public ProductHelper(Product product) {
		this.product = product;
	}
	
	public void fillBarCodes(List<BarCode> barCodes){
		for (BarCode barCode : barCodes){
			barCode.setProduct(product);
		}
		product.setBarCodes(barCodes);
	}
	
}
