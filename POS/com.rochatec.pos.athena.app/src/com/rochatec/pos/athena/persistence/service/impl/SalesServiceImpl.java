package com.rochatec.pos.athena.persistence.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rochatec.pos.athena.persistence.model.BarCode;
import com.rochatec.pos.athena.persistence.model.Box;
import com.rochatec.pos.athena.persistence.model.Operator;
import com.rochatec.pos.athena.persistence.model.Product;
import com.rochatec.pos.athena.persistence.repository.IBoxRepository;
import com.rochatec.pos.athena.persistence.repository.IProductRepository;
import com.rochatec.pos.athena.persistence.service.ISaleService;

@Service
public class SalesServiceImpl implements ISaleService{
	
	@Autowired
	private IProductRepository productRepository; 
	
	@Autowired
	private IBoxRepository boxRepository;
	
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

	@Override
	public Box persist(Box box) {
		box = boxRepository.persist(box);
		return box;
	}

	@Override
	public void remove(Box box) {
		boxRepository.remove(box);		
	}

	@Override
	public Box findBoxById(Long id) {
		Box box = boxRepository.findById(id);
		return box;
	}

	@Override
	public List<Box> findAllBoxes() {
		List<Box> boxes = boxRepository.findAll();
		return boxes;
	}

	@Override
	public Box findBoxByOperatorAndOpen(Operator operator) {
		Box box = boxRepository.findByOperatorAndOpen(operator);
		return box;
	}
	
}
