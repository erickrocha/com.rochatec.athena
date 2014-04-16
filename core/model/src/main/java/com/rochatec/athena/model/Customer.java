package com.rochatec.athena.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rochatec.metallurgical.util.CalendarUtil;
import com.rochatec.metallurgical.util.StringUtils;

@Entity
@Table(name = "CUSTOMER")
@NamedQueries({
	@NamedQuery(name="Customer.findByName",query="SELECT c FROM Customer c WHERE c.name like :name"),
	@NamedQuery(name="Customer.findBySocialSecurity",query="SELECT c FROM Customer c WHERE c.socialSecurity = :socialSecurity")
})
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1055462334590449238L;
	
	@Id
	@SequenceGenerator(name="SEQ_CUSTOMER", sequenceName="SEQ_CUSTOMER",allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CUSTOMER")
	private Long id;
	
	@Column(name="SOCIAL_SECURITY",length=20)
	private String socialSecurity;
	
	@Column(name="NAME",length=80)
	private String name;
	
	@Column(name="REGISTER_NUMBER",length=20)
	private String registerNumber;
	
	@Column(name="CITY_REGISTER",length=20)
	private String cityRegister;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ADDRESS", nullable=false)
	private Address address;
	
	@Column(name="ADDRESS_NUMBER",length=10)
	private String addressNumber;
	
	@Column(name="ZIPCODE",length=20)
	private String zipcode;
	
	@Temporal( TemporalType.DATE)
	@Column(name="DATE_REGISTER")
	private Calendar dateRegister;
	
	@Column(name="EMAIL",length=70)
	private String email;
	
	@Column(name="HOME_PHONE",length=20)
	private String homePhone;
	
	@Column(name="CELL_PHONE",length=20)
	private String cellPhone;
	
	public Customer() {
		
	}
	
	public Customer(String socialSecurity, String name,
			String registerNumber, String cityRegister, Address address,
			String addressNumber, String zipcode, Calendar dateRegister,
			String email, String homePhone, String cellPhone) {
		super();		
		this.socialSecurity = socialSecurity;
		this.name = name;
		this.registerNumber = registerNumber;
		this.cityRegister = cityRegister;
		this.address = address;
		this.addressNumber = addressNumber;
		this.zipcode = zipcode;
		this.dateRegister = dateRegister;
		this.email = email;
		this.homePhone = homePhone;
		this.cellPhone = cellPhone;
	}

	public Customer(String socialSecurity, String name,
			String registerNumber, String cityRegister, Address address,
			String addressNumber, String zipcode,String email, String homePhone, String cellPhone) {
		this(socialSecurity,name,registerNumber,cityRegister,address,addressNumber,zipcode,CalendarUtil.getToday(),email,homePhone,cellPhone);		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = StringUtils.onlyNumber(socialSecurity);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber.trim();
	}

	public Address getAddress() {
		this.address.setAddressNumber(addressNumber);
		this.address.setZipcode(zipcode);
		return address;
	}

	public void setAddress(Address address) {
		setAddressNumber(address.getAddressNumber());
		setZipcode(address.getZipcode());
		this.address = address;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber.trim();
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = StringUtils.onlyNumber(zipcode);
	}

	public Calendar getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Calendar dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase().trim();
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = StringUtils.onlyNumber(homePhone);
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = StringUtils.onlyNumber(cellPhone);
	}

	public String getCityRegister() {
		return cityRegister;
	}

	public void setCityRegister(String cityRegister) {
		this.cityRegister = cityRegister;
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
		Customer other = (Customer) obj;
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
