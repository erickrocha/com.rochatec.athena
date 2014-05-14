package com.rochatec.athena.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SALE_ORDER_ITEM")
public class SaleOrderItem implements Serializable,IProductItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7284475245900315352L;
	
	@Id
	@SequenceGenerator(name="SEQ_SALES_ORDER_ITEM", sequenceName="SEQ_SALES_ORDER_ITEM",allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SALES_ORDER_ITEM")
	@Column(unique=true, nullable=false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "SALE_ORDER", nullable = false, updatable = false)
	private SaleOrder saleOrder;

	@OneToOne
	@JoinColumn(name = "PRODUCT", nullable = false)
	private AbstractProduct product;
	
	@Column(name = "QUANTITY", nullable = false, precision = 10, scale = 3)
	private BigDecimal quantity;
	
	@Column(name="SELL_PRICE",precision=10,scale=2)
	private BigDecimal sellPrice;

	public SaleOrderItem() {
	}

	public SaleOrderItem(Long id, SaleOrder saleOrder, AbstractProduct product,
			BigDecimal quantity, BigDecimal sellPrice) {
		super();
		this.id = id;
		this.saleOrder = saleOrder;
		this.product = product;
		this.quantity = quantity;
		this.sellPrice = sellPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SaleOrder getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
	}

	public AbstractProduct getProduct() {
		return product;
	}

	public void setProduct(AbstractProduct product) {
		this.product = product;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public BigDecimal getTotalItem(){		
		return this.quantity.multiply(sellPrice);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result
				+ ((saleOrder == null) ? 0 : saleOrder.hashCode());
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
		SaleOrderItem other = (SaleOrderItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (saleOrder == null) {
			if (other.saleOrder != null)
				return false;
		} else if (!saleOrder.equals(other.saleOrder))
			return false;
		return true;
	}

	@Override	
	public String toString() {
		return product.getName();
	}

	@Override
	public String getLabel() {		
		return product.getName();
	}

	@Override
	public Object getParent() {
		return saleOrder;
	}

	@Override
	public Icms getIcms() {
		return product.getIcms();
	}

	@Override
	public BigDecimal getIpi() {
		return new BigDecimal(product.getIpi());
	}

	@Override
	public Long getProductId() {
		return getProduct().getId();
	}
	
	
}
