package com.rochatec.athena.pdv.service.model;

import java.math.BigDecimal;

public class Payment {

	private String key;
	private PaymentForm paymentForm;
	private BigDecimal value;

	public Payment() {
		super();
	}

	public Payment(String key, PaymentForm paymentForm, BigDecimal value) {
		super();
		this.key = key;
		this.paymentForm = paymentForm;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public PaymentForm getPaymentForm() {
		return paymentForm;
	}

	public void setPaymentForm(PaymentForm paymentForm) {
		this.paymentForm = paymentForm;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
