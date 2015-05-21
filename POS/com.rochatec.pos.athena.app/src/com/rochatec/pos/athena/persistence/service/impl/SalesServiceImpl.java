package com.rochatec.pos.athena.persistence.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rochatec.pos.athena.persistence.model.BarCode;
import com.rochatec.pos.athena.persistence.model.Product;
import com.rochatec.pos.athena.persistence.repository.IProductRepository;
import com.rochatec.pos.athena.persistence.service.ISaleService;

@Service
public class SalesServiceImpl implements ISaleService{
	
	@Autowired
	private IProductRepository productRepository; 
	
	@Override
	public Product findProductById(Long id) {
		Product product = productRepository.findById(id);
		return product;
	}

	@Override
	public Product findProductByBarcode(BarCode barCode) {
		Product product = productRepository.findByBarcode(barCode);
		return product;
	}

	@Override
	public Product findProductByBarcode(String barcode) {
		Product product = productRepository.findByBarcode(barcode);
		return product;
	}

	@Override
	public Set<Product> findProductsByName(String name) {
		Set<Product> products = productRepository.findByName(name);
		return products;
	}
	
}
