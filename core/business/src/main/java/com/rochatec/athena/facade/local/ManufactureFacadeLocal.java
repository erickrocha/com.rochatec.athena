package com.rochatec.athena.facade.local;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.AbstractProduct;
import com.rochatec.athena.model.Category;
import com.rochatec.athena.model.Icms;
import com.rochatec.athena.model.Ncm;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.model.ProductSet;
import com.rochatec.athena.model.ProductSetItem;
import com.rochatec.athena.model.Status;

@Local
public interface ManufactureFacadeLocal {

	public Category persist(Category category);

	public void remove(Category category);

	public Category findCategoryById(Long id);

	public List<Category> findCategoriesByName(String name);

	public List<Category> findAllCategories();

	
	public List<Icms> findAllIcms();

	
	public Ncm persist(Ncm ncm);

	public void remove(Ncm ncm);

	public Ncm findNcmById(String id);

	public List<Ncm> findAllNcms();

	public List<Ncm> findNcmsByName(String name);

	
	public Product persist(Product product);

	public void remove(Product product);

	public Product findProductById(Long id);

	public List<Product> findProductsByName(String name, Calendar begin, Calendar end, Status status);

	public List<Product> findProductsByCategory(String category, Calendar begin, Calendar end, Status status);
	
	public List<Product> findProductsByDateRegister(Calendar begin, Calendar end, Status status);
	
	public Product findProductByUniqueKey(Long id,String barcode);
	
	
	public ProductSet persist(ProductSet productSet);

	public void remove(ProductSet productSet);

	public ProductSet findProductSetById(Long id);

	public List<ProductSet> findProductSetsByName(String name, Calendar begin, Calendar end, Status status);

	public List<ProductSet> findProductSetsByCategory(String category, Calendar begin, Calendar end, Status status);
	
	public List<ProductSet> findProductSetsByDateRegister(Calendar begin, Calendar end, Status status);
	
	public List<ProductSetItem> findProductSetItems(ProductSet productSet);
		
	public AbstractProduct findById(Long id);
	
	public List<AbstractProduct> findByName(String name, Calendar begin, Calendar end, Status status);
	
	public List<AbstractProduct> findByCategory(String category, Calendar begin, Calendar end, Status status);

}
