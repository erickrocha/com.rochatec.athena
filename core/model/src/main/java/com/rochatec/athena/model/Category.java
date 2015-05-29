package com.rochatec.athena.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 * The persistent class for the CATEGORY database table.
 * 
 */
@Entity
@Table(name="CATEGORY")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_CATEGORY", sequenceName="SEQ_CATEGORY",allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CATEGORY")
	@Column(updatable=false, unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=30)
	private String name;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="category",fetch=FetchType.LAZY)
    @XmlElement
	private List<AbstractProduct> products;

    public Category() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AbstractProduct> getProducts() {
		return this.products;
	}

	public void setProducts(List<AbstractProduct> products) {
		this.products = products;
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}