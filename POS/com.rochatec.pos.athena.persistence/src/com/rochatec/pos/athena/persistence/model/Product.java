package com.rochatec.pos.athena.persistence.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID",unique=true)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private Status status = Status.ACTIVE;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_REGISTER")
	private Calendar dateRegister;

	@OneToMany(mappedBy="product",fetch=FetchType.LAZY)
	private Set<BarCode> barCodes;

	@ManyToOne
	@JoinColumn(name="ICMS", nullable=false)
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
