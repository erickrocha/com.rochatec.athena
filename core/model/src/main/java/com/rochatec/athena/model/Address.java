package com.rochatec.athena.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the ADDRESS database table.
 * 
 */
@Entity
@Table(name="ADDRESS")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_ADDRESS", sequenceName="SEQ_ADDRESS",allocationSize =1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ADDRESS")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=50)
	private String city;

	@Column(length=50)
	private String neighborhood;

	@Column(length=60)
	private String street;
	
	@Column(name="ZIPCODE",length=8)
	private String zipcode;
	
	@Transient
	private String addressNumber;
	
	@Column(name="COMPLEMENT",length=200)
	private String complement;

	//bi-directional many-to-one association to Province
    @ManyToOne
	@JoinColumn(name="PROVINCE")
    @XmlElement
	private Province province;

    public Address() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return this.city != null ? this.city : "";
	}

	public void setCity(String city) {
		this.city = city.trim();
	}

	public String getNeighborhood() {
		return this.neighborhood != null ? neighborhood : "";
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood.trim();
	}

	public String getStreet() {
		return this.street != null ? this.street : "";
	}

	public void setStreet(String street) {
		this.street = street.trim();
	}

	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getZipcode() {
		return zipcode != null ? zipcode : "";
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode.trim();
	}

	public String getAddressNumber() {
		return addressNumber != null ? addressNumber : "";
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}
	

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement.trim();
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
		Address other = (Address) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return street+" "+neighborhood+" "+city+" "+province.getAcronym();
	}
	
}