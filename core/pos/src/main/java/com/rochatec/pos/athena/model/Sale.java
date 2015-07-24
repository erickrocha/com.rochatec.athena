package com.rochatec.pos.athena.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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

	@ManyToOne
	@JoinColumn(name="OPERATOR", nullable=false)
	private Operator operador;
	
	@OneToOne
	@JoinColumn(name = "CUSTOMER", referencedColumnName = "ID")
	private Customer customer;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private StatusSale status;
	
	@Column(name="TOTAL",scale=20,precision=2)
	private BigDecimal total = BigDecimal.ZERO;
	
	@OneToMany(mappedBy="sale",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
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
			if (item.getStatus().equals(ItemStatus.OK)){
				subTotal = subTotal.add(item.getTotalItem());
			}
		}
		return subTotal;
	}
	
	public Operator getOperador() {
		return operador;
	}

	public void setOperador(Operator operador) {
		this.operador = operador;
	}

	public StatusSale getStatus() {
		return status;
	}

	public void setStatus(StatusSale status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addItem(ItemSale itemSale){
		if (items == null){
			items = new HashSet<>();
		}
		items.add(itemSale);
	}

	public BigDecimal calculateSubtTotal(){
		BigDecimal value = BigDecimal.ZERO;
		for (ItemSale item : items){
			if (item.getStatus().equals(ItemStatus.OK)){
				BigDecimal totalItem = item.getQuantity().multiply(item.getSellPrice());
				value.add(totalItem);
			}
		}
		return value;
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
