package com.rochatec.pos.athena.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SALE")
public class Sale implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_REGISTER")
	private Date dateRegister;
	
	@Column(name="OPERADOR")
	private String operador;
	
	@OneToOne
	@JoinColumn(name = "CUSTOMER", referencedColumnName = "ID")
	private Customer customer;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private Status status; 
	
	@Column(name="TOTAL",scale=20,precision=2)
	private BigDecimal total = BigDecimal.ZERO;
	
	@OneToMany(mappedBy="sale",fetch=FetchType.LAZY)	
	private Set<ItemSale> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public Set<ItemSale> getItems() {
		return items;
	}

	public void setItems(Set<ItemSale> items) {
		this.items = items;
	}
	
	public BigDecimal getSubTotal(){
		BigDecimal subTotal = BigDecimal.ZERO;
		for (ItemSale item : items){
			subTotal = subTotal.add(item.getTotalItem());
		}
		return subTotal;
	}
	
	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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
		Sale other = (Sale) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
