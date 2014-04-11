package com.rochatec.athena.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the SHIPPERS database table.
 * 
 */
@Entity
@Table(name = "SHIPPER")
@NamedQueries({
		@NamedQuery(name = "Shipper.findBySocialSecurity", query = "SELECT s FROm Shipper s WHERE s.socialSecurity = :socialSecurity") })
public class Shipper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_SHIPPER", sequenceName = "SEQ_SHIPPER", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHIPPER")
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "ADDRESS_NUMBER", length = 10)
	private String addressNumber;

	@Column(name = "BUSINESS_NAME", nullable = false, length = 60)
	private String businessName;

	@Column(name = "CITY_REGISTER", length = 20)
	private String cityRegister;

	@Column(name = "COMERCIAL_NAME", length = 60)
	private String comercialName;

	@Column(length = 20,name="PHONE")
	private String phone;

	@Column(name = "REGISTER_NUMBER", length = 20)
	private String registerNumber;

	@Column(name = "SOCIAL_SECURITY", nullable = false, length = 20)
	private String socialSecurity;

	@Column(length = 10,name="ZIPCODE")
	private String zipcode;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_REGISTER")
	private Calendar dateRegister;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private Status status;

	@Column(name = "EMAIL", length = 70)
	private String email;

	@Column(name = "FAX",length=20)
	private String fax;

	// bi-directional many-to-one association to Address
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ADDRESS", nullable = false)
	private Address address;

	public Shipper() {
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

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName.trim();
	}

	public String getCityRegister() {
		return this.cityRegister != null ? this.cityRegister : "";
	}

	public void setCityRegister(String cityRegister) {
		this.cityRegister = cityRegister.trim();
	}

	public String getComercialName() {
		return this.comercialName != null ? this.comercialName : "";
	}

	public void setComercialName(String comercialName) {
		this.comercialName = comercialName.trim();
	}

	public String getPhone() {
		return this.phone != null ? this.phone : "";
	}

	public void setPhone(String phone) {
		this.phone = phone.trim();
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
		this.socialSecurity = socialSecurity.trim();
	}

	public String getZipcode() {
		return this.zipcode != null ? this.zipcode : "";
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode.trim();
	}

	public Address getAddress() {
		address.setAddressNumber(getAddressNumber());
		address.setZipcode(address.getZipcode());
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
		setAddressNumber(address.getAddressNumber());
		setZipcode(address.getZipcode());
	}

	public Calendar getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Calendar dateRegister) {
		dateRegister.add(Calendar.HOUR,0);
		dateRegister.add(Calendar.MINUTE,0);
		dateRegister.add(Calendar.SECOND,0);
		dateRegister.add(Calendar.MILLISECOND,0);
		this.dateRegister = dateRegister;
	}
	
	public void setDateRegister(String dateString){
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		try {
			Date date = df.parse(dateString);
			dateRegister.add(Calendar.HOUR,0);
			dateRegister.add(Calendar.MINUTE,0);
			dateRegister.add(Calendar.SECOND,0);
			dateRegister.add(Calendar.MILLISECOND,0);
			dateRegister.setTime(date);
		} catch (ParseException e) {			
			e.printStackTrace();
		} 
	}

	public Status getStatus() {
		return status;
	}
	
	public boolean isActive(){
		return status.equals(Status.ACTIVE);
	}
	
	public void setActive(boolean active){
		this.status = active ? Status.ACTIVE : Status.INACTIVE;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase().trim();
	}

	public String getFax() {
		return fax != null ? this.fax : "";
	}

	public void setFax(String fax) {
		this.fax = fax.trim();
	}
	
	@Override
	public String toString() {
		return this.comercialName != null ? this.comercialName : this.businessName;
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
		Shipper other = (Shipper) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}