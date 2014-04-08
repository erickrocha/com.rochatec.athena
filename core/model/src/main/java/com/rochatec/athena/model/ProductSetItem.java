package com.rochatec.athena.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "PRODUCT")
public class ProductSetItem extends AbstractItem implements Serializable, IProductItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7105008697607451904L;

	@OneToOne
	@JoinColumn(name = "PARENT", nullable = false, updatable = false)
	private AbstractProduct parent;

	@OneToOne
	@JoinColumn(name = "PRODUCT", nullable = false, updatable = false)
	private AbstractProduct product;

	public AbstractProduct getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public AbstractProduct getParent() {
		return parent;
	}

	public void setParent(ProductSet parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		return product.getName()+" "+getQuantity();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductSetItem other = (ProductSetItem) obj;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public String getLabel() {
		return product.getName();
	}

	@Override
	public BigDecimal getSellPrice() {
		return product.getSellprice();
	}

	@Override
	public Icms getIcms() {
		return product.getIcms();
	}

	@Override
	public BigDecimal getIpi() {
		BigDecimal ipi = new BigDecimal(product.getIpi());
		ipi.setScale(2,RoundingMode.HALF_EVEN);
		return ipi;
	}

	@Override
	public BigDecimal getTotalItem() {		
		return getSellPrice().multiply(getQuantity());
	}

}
