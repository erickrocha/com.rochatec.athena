package com.rochatec.athena.pdv.lib.model;

import java.math.BigDecimal;

public class Increase implements IncreaseOrDiscount{
	
	private BigDecimal value;
	
	
	@Override
	public IncreateOrDiscountType getType() { 
		return IncreateOrDiscountType.INCREASE;
	}

	@Override
	public BigDecimal getValue() { 
		return this.value;
	}

	@Override
	public String getValueString() {
		return this.value.toString();
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	

}
