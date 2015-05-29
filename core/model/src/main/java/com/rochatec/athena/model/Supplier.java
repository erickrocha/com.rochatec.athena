package com.rochatec.athena.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.rochatec.athena.util.StringUtils;


/**
 * The persistent class for the SUPPLIER database table.
 * 
 */
@Entity
@Table(name="SUPPLIER")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_SUPPLIER", sequenceName="SEQ_SUPPLIER",allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SUPPLIER")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(name="ADDRESS_NUMBER", length=10)
	private String addressNumber;

	@Column(name="CITY_REGISTER", length=20)
	private String cityRegister;

	@Column(name="COMPANY_NAME", length=60)
	private String companyName;

    @Temporal( TemporalType.DATE)
	@Column(name="DATE_REGISTER", nullable=false)
	private Calendar dateRegister;

	@Column(length=10,name="FAX")
	private String fax;

	@Column(length=10,name="PHONE")
	private String phone;

	@Column(name="REGISTER_NUMBER", length=20)
	private String registerNumber;

	@Column(name="SOCIAL_SECURITY", nullable=false, length=20)
	private String socialSecurity;

	@Column(name="TRADE_NAME", nullable=false, length=60)
	private String tradeName;

	@Column(name="WEB_SITE",length=70)
	private String website;

	@Column(length=10)
	private String zipcode;
	
	@Enumerated(EnumType.STRING )
	@Column(name="STATUS")
	private Status status;

	//bi-directional many-to-one association to Address
    @ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ADDRESS", nullable=false)
    @XmlElement
	private Address address;

    public Supplier() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressNumber() {
		return this.addressNumber != null ? this.addressNumber : "";
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber.trim();
	}

	public String getCityRegister() {
		return this.cityRegister != null ? this.cityRegister : "";
	}

	public void setCityRegister(String cityRegister) {
		this.cityRegister = cityRegister.trim();
	}

	public String getCompanyName() {
		return this.companyName != null ? this.companyName : "";
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName.trim();
	}

	public Calendar getDateRegister() {
		return this.dateRegister;
	}

	public void setDateRegister(Calendar dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getFax() {
		return this.fax != null ? this.fax : "";
	}

	public void setFax(String fax) {
		this.fax = StringUtils.onlyNumber(fax);
	}

	public String getPhone() {
		return this.phone != null ? this.phone : "";
	}

	public void setPhone(String phone) {
		this.phone = StringUtils.onlyNumber(phone);
	}

	public String getRegisterNumber() {
		return this.registerNumber != null ? this.registerNumber : "";
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber.trim();
	}

	public String getSocialSecurity() {
		return this.socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = StringUtils.onlyNumber(socialSecurity);
	}

	public String getTradeName() {
		return this.tradeName != null ? this.tradeName : "";
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName.trim() ;
	}

	public String getWebsite() {
		return this.website != null ? this.website : "";
	}

	public void setWebsite(String website) {
		this.website = website.trim();
	}

	public String getZipcode() {
		return this.zipcode != null ? this.zipcode : "";
	}

	public void setZipcode(String zipcode) {
		this.zipcode = StringUtils.onlyNumber(zipcode);
	}

	public Address getAddress() {
		this.address.setAddressNumber(getAddressNumber());
		this.address.setZipcode(getZipcode());
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
		setAddressNumber(address.getAddressNumber());
		setZipcode(address.getZipcode());
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public boolean isActive(){
		return this.status.equals(Status.ACTIVE);
	}
	
	public void setActive(boolean active){
		this.status = active ? Status.ACTIVE : Status.INACTIVE;
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
		Supplier other = (Supplier) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return tradeName != null && !tradeName.equals("") ? tradeName : companyName;
	}
	
}