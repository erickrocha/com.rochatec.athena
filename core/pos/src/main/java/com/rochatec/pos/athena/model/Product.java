package com.rochatec.pos.athena.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
	
	@ManyToOne
	@JoinColumn(name="CATEGORY", nullable=false)
	private Category category;

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private StatusSale status;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SHORT_NAME")
	private String shortName;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_REGISTER")
	private Calendar dateRegister;
	
	@Column(name="SELL_PRICE",length=10,precision=2)
	private BigDecimal sellPrice = BigDecimal.ZERO;

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

	public StatusSale getStatus() {
		return status;
	}

	public void setStatus(StatusSale status) {
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

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
