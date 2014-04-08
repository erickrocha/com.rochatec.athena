package com.rochatec.athena.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "SET")
public class ProductSet extends AbstractProduct {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6459586584076087964L;
	
	
	@OneToMany(mappedBy = "parent",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<ProductSetItem> children;
	
	public Set<ProductSetItem> getChildren() {
		return children;
	}
	
	public List<ProductSetItem> getListChildren(){
		return new ArrayList<ProductSetItem>(children);
	}

	public void setChildren(Set<ProductSetItem> children) {
		this.children = children;
	}
	
	public void setListChildren(List<ProductSetItem> children) {
		this.children = new HashSet<ProductSetItem>(children);
	}
	
	public List<IProductItem> getProducts(){
		List<IProductItem> items = new ArrayList<IProductItem>();
		for (ProductSetItem item : getChildren()){
			items.add(item);
		}
		return items;
	}

}
