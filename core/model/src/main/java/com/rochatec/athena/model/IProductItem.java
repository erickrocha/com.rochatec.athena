package com.rochatec.athena.model;

import java.math.BigDecimal;

public interface IProductItem {

	public String getLabel();
	
	public BigDecimal getQuantity();
	
	public BigDecimal getSellPrice();
	
	public Long getId();
	
	public Object getParent();
	
	public Icms getIcms();
	
	public BigDecimal getIpi();
	
	public BigDecimal getTotalItem();
	
	public Long getProductId();
	
}
