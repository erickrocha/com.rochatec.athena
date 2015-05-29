package com.rochatec.athena.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "BARCODE")
@SequenceGenerator(name="SEQ_BARCODE",sequenceName="SEQ_BARCODE",initialValue=1,allocationSize=1)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BarCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7763367583162705634L;
	
	@Id
	@Column(name="ID",updatable = false,insertable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_BARCODE")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT", nullable=false)
    @XmlElement
	private Product product;

	@Column(name="BARCODE",nullable=false)
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
