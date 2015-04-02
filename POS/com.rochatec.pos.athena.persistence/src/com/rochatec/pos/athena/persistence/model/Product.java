package com.rochatec.pos.athena.persistence.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

/**
 * Created by epr on 31/10/14.
 */

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Status status = Status.ACTIVE;

	private Calendar dateRegister;

	private Set<BarCode> barCodes;

	protected Icms icms;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Calendar getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Calendar dateRegister) {
		this.dateRegister = dateRegister;
	}

	public Set<BarCode> getBarCodes() {
		return barCodes;
	}

	public void setBarCodes(Set<BarCode> barCodes) {
		this.barCodes = barCodes;
	}

	public Icms getIcms() {
		return icms;
	}

	public void setIcms(Icms icms) {
		this.icms = icms;
	}

}
