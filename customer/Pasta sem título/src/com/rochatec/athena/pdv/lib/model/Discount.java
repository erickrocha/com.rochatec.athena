package com.rochatec.athena.pdv.lib.model;

import java.math.BigDecimal;

public class Discount implements IncreaseOrDiscount{
	
	private BigDecimal value;

	@Override
	public IncreateOrDiscountType getType() {
		return IncreateOrDiscountType.DISCOUNT;
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	@Override
	public String getValueString() {
		return value.toString();
	}

}
