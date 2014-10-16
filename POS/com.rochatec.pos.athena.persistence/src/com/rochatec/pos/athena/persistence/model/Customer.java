package com.rochatec.pos.athena.persistence.model;

public class Customer {

	private String name;
	private String socialSecurity;
	private String address;
	
	public Customer() {
		
	}

	public Customer(String name, String socialSecurity, String address) {
		super();
		this.name = name;
		this.socialSecurity = socialSecurity;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((socialSecurity == null) ? 0 : socialSecurity.hashCode());
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
		if (socialSecurity == null) {
			if (other.socialSecurity != null)
				return false;
		} else if (!socialSecurity.equals(other.socialSecurity))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
