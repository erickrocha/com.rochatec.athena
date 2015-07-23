package com.rochatec.pos.athena.service;

import com.rochatec.pos.athena.model.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

 public interface ISaleService {

	 Product findProductById(Long id);

	 Product findProductByBarcode(BarCode barCode);

	 Product findProductByBarcode(String barcode);

	 Set<Product> findProductsByName(String name);
	
	 Box persist(Box box);
	
	 void remove(Box box);
	
	 Box findBoxById(Long id);
	
	 List<Box> findAllBoxes();
	
	 Box findBoxByOperatorAndOpen(Operator operator);

     Customer persist(Customer customer);

     void remove(Customer customer);

     Customer findCustomer(Long id);

     Set<Customer> findAllCustomersByName(String name);

     Sale persist(Sale sale);

     void remove(Sale sale);

     Sale findSale(Long id);

     Set<Sale> findAllSalesByDay(Date begin,Date end);

}
