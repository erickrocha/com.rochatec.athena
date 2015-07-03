package com.rochatec.pos.athena.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BARCODE")
public class BarCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7763367583162705634L;
	
	@Id
	@Column(name="ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT", nullable=false)	
	private Product product;
	
	@Column(name="BARCODE",unique=true)
	private String barcode;

	public BarCode() {
	}

	public BarCode(Product product, String barcode) {
		super();
		this.product = product;
		this.barcode = barcode;
	}

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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BarCode other = (BarCode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	@Override
	public String toString() {		
		return barcode;
	}

}
