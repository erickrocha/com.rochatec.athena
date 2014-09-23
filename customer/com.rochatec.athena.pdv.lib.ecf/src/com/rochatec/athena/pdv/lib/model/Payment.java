package com.rochatec.athena.pdv.lib.model;

import java.math.BigDecimal;

public class Payment {

	private String name;
	private BigDecimal value;
	private Integer index;
	private String description;

	public Payment() {

	}

	public Payment(Integer index, String name, BigDecimal value,String description) {
		super();
		this.index = index;
		this.name = name;
		this.value = value;
		this.description = description;
	}
	
	public Payment(Integer index, String name, BigDecimal value) {
		super();
		this.index = index;
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

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
