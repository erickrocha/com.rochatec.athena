package com.rochatec.athena.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INVOICE_ITEM")
@DiscriminatorColumn(name = "ITEM_TYPE", discriminatorType = DiscriminatorType.STRING)
public class AbstractInvoiceItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6571754785362579347L;

	@SequenceGenerator(sequenceName = "SEQ_INVOICE_ITEM", name = "SEQ_INVOICE_ITEM", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INVOICE_ITEM")
	@Id
	@Column(name = "ID")
	private Long id;

	@OneToOne
	@JoinColumn(name = "PRODUCT", referencedColumnName = "ID")
	private Product product;

	@Column(name = "QUANTITY", precision = 10, scale = 3)
	private BigDecimal quantity = BigDecimal.ZERO;

	@Column(name = "TOTAL_PRODUCT", precision = 10, scale = 2)
	private BigDecimal totalProduct = BigDecimal.ZERO;

	@Column(name = "IPI_BASE", precision = 10, scale = 2)
	private BigDecimal ipiBase = BigDecimal.ZERO;

	@Column(name = "IPI_VALUE", precision = 10, scale = 3)
	private BigDecimal ipiValue = BigDecimal.ZERO;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private ItemStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(BigDecimal totalProduct) {
		this.totalProduct = totalProduct;
	}

	public BigDecimal getIpiValue() {
		return ipiValue;
	}

	public void setIpiValue(BigDecimal ipiValue) {
		this.ipiValue = ipiValue;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}

	public BigDecimal getIpiBase() {
		return ipiBase;
	}

	public void setIpiBase(BigDecimal ipiBase) {
		this.ipiBase = ipiBase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		AbstractInvoiceItem other = (AbstractInvoiceItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
	


}
