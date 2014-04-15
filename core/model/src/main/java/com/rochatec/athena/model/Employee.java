package com.rochatec.athena.model;

import java.io.Serializable;
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
 * The persistent class for the EMPLOYEE database table.
 * 
 */
@Entity
@Table(name = "EMPLOYEE")
@NamedQueries({
		@NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE e.name like :name"),
		@NamedQuery(name = "Employee.findBySocialSecurity", query = "SELECT e FROM Employee e WHERE e.socialSecurity = :socialSecurity") })
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_EMPLOYEE", sequenceName = "SEQ_EMPLOYEE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMPLOYEE")
	@Column(unique = true, nullable = false)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private Status status;

	@Column(name = "ADDRESS_NUMBER", length = 10)
	private String addressNumber;

	@Column(name = "CELL_PHONE", length = 20)
	private String cellPhone;

	@Column(length = 70)
	private String email;

	@Column(name = "HIRE_DATE")
	@Temporal(TemporalType.DATE)
	private Calendar hiredate;

	@Column(name = "HOME_PHONE", length = 20)
	private String homePhone;

	@Column(nullable = false, length = 80)
	private String name;

	@Column(name = "REGISTER_NUMBER", length = 10)
	private String registerNumber;

	@Column(name = "RESIGNATION_DATE")
	@Temporal(TemporalType.DATE)
	private Calendar resignationDate;

	@Column(name = "SOCIAL_SECURITY", nullable = false, length = 20)
	private String socialSecurity;

	@Column(length = 10)
	private String zipcode;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ADDRESS", nullable = false)
	private Address address;

	// bi-directional many-to-one association to Job
	@ManyToOne
	@JoinColumn(name = "JOB", nullable = false)
	private Job job;

	public Employee() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getAddressNumber() {
		return this.addressNumber != null ? this.addressNumber : "";
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber.trim();
	}

	public String getCellPhone() {
		return this.cellPhone != null ? this.cellPhone : "";
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone.trim();
	}

	public String getEmail() {
		return this.email != null ? this.email : "";
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public Calendar getHiredate() {
		return hiredate;
	}

	public void setHiredate(Calendar hiredate) {
		hiredate.add(Calendar.HOUR,0);
		hiredate.add(Calendar.MINUTE,0);
		hiredate.add(Calendar.SECOND,0);
		hiredate.add(Calendar.MILLISECOND,0);
		this.hiredate = hiredate;
	}
	
	public void setHiredate(Date date){
		hiredate.add(Calendar.HOUR,0);
		hiredate.add(Calendar.MINUTE,0);
		hiredate.add(Calendar.SECOND,0);
		hiredate.add(Calendar.MILLISECOND,0);
		this.hiredate.setTime(date);
	}

	public void setResignationDate(Calendar resignationDate) {
		resignationDate.add(Calendar.HOUR,0);
		resignationDate.add(Calendar.MINUTE,0);
		resignationDate.add(Calendar.SECOND,0);
		resignationDate.add(Calendar.MILLISECOND,0);
		this.resignationDate = resignationDate;
	}

	public String getHomePhone() {
		return this.homePhone != null ? this.homePhone : "";
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone.trim();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
		this.socialSecurity = socialSecurity;
	}

	public String getZipcode() {
		return this.zipcode != null ? this.zipcode : "";
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode.trim();
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

	public Job getJob() {
		return this.job;
	}

	public Calendar getResignationDate() {
		return resignationDate;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
	public void setActive(boolean active){
		this.status = active ? Status.ACTIVE : Status.INACTIVE;
	}
	
	public boolean isActive(){
		return this.status.equals(Status.ACTIVE) ? true : false;
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
		Employee other = (Employee) obj;
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