package com.rochatec.pos.athena.persistence.model;

import java.math.BigDecimal;

public class Payment {

	private String name;
	private BigDecimal value;

	public Payment() {

	}

	public Payment(String name, BigDecimal value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
