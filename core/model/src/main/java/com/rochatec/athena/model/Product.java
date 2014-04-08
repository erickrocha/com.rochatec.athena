package com.rochatec.athena.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="PRODUCT")
@NamedQueries({
	@NamedQuery(name="Product.findByName",query="SELECT p FROM Product p WHERE p.name like :name")
})
public class Product extends AbstractProduct{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3279494255517340948L;

	
}
