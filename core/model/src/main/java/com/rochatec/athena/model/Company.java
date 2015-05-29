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

@Entity
@Table(name = "COMPANY")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8737157163680637164L;

	@Id
	@SequenceGenerator(name = "SEQ_COMPANY", sequenceName = "SEQ_COMPANY", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPANY")
	@Column(unique = true, nullable = false)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_REGISTER", nullable = false)
	private Calendar dateRegister;

	@Column(name = "TRADE_NAME", nullable = false, length = 60)
	private String tradeName;

	@Column(name = "COMPANY_NAME", length = 60)
	private String companyName;

	@Column(name = "SOCIAL_SECURITY", nullable = false, length = 20)
	private String socialSecurity;

	@Column(name = "REGISTER_NUMBER", length = 20)
	private String registerNumber;

	@Column(name = "CITY_REGISTER", length = 20)
	private String cityRegister;

	// bi-directional many-to-one association to Address
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS", nullable = false)
    @XmlElement
	private Address address;

	@Column(name = "ADDRESS_NUMBER", length = 10)
	private String addressNumber;

	@Column(length = 20)
	private String zipcode;

	@Column(length = 20)
	private String phone;

	@Column(length = 20)
	private String fax;

	@Column(name="WEB_SITE",length = 70)
	private String website;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Calendar dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getCityRegister() {
		return cityRegister;
	}

	public void setCityRegister(String cityRegister) {
		this.cityRegister = cityRegister;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
