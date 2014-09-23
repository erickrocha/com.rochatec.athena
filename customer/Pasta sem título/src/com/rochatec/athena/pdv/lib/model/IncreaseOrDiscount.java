package com.rochatec.athena.pdv.lib.model;

import java.math.BigDecimal;

public interface IncreaseOrDiscount {

	
	public IncreateOrDiscountType getType();
	
	public BigDecimal getValue();
	
	public String getValueString();
		
}
