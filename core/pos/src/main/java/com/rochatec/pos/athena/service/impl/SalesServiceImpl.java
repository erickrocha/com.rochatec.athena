package com.rochatec.pos.athena.service.impl;

import com.rochatec.pos.athena.model.*;
import com.rochatec.pos.athena.repository.IBoxRepository;
import com.rochatec.pos.athena.repository.ICustomerRepository;
import com.rochatec.pos.athena.repository.IProductRepository;
import com.rochatec.pos.athena.repository.ISaleRepository;
import com.rochatec.pos.athena.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class SalesServiceImpl implements ISaleService{
	
	@Autowired
	private IProductRepository productRepository; 
	
	@Autowired
	private IBoxRepository boxRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ISaleRepository saleRepository;
	
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
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,noRollbackFor = Exception.class,readOnly = false)
	public Box persist(Box box) {
		box = boxRepository.persist(box);
		return box;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,noRollbackFor = Exception.class,readOnly = false)
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

	@Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,noRollbackFor = Exception.class,readOnly = false)
	public Customer persist(Customer customer) {
		customer = customerRepository.persist(customer);
		return customer;
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,noRollbackFor = Exception.class,readOnly = false)
	public void remove(Customer customer) {
        customerRepository.remove(customer);
	}

	@Override
	public Customer findCustomer(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public Set<Customer> findAllCustomersByName(String name) {
        Set<Customer> customers = customerRepository.findAllByName(name);
		return customers;
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,noRollbackFor = Exception.class,readOnly = false)
    public Sale persist(Sale sale) {
        sale = saleRepository.persist(sale);
        return sale;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,noRollbackFor = Exception.class,readOnly = false)
    public void remove(Sale sale) {
        saleRepository.remove(sale);
    }

    @Override
    public Sale findSale(Long id) {
        Sale sale = saleRepository.findById(id);
        return sale;
    }

    @Override
    public Set<Sale> findAllSalesByDay(Date begin, Date end) {
        Set<Sale> sales = saleRepository.findAllByDay(begin,end);
        return sales;
    }
}
