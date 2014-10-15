package com.rochatec.athena.pdv.service.model;

public class PaymentForm {

	private String key;
	private String name;
	private Boolean returnMoney;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getReturnMoney() {
		return returnMoney;
	}

	public void setReturnMoney(Boolean returnMoney) {
		this.returnMoney = returnMoney;
	}

}
