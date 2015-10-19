package com.rochatec.athena.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the PROVINCE database table.
 * 
 */
@Entity
@Table(name="PROVINCE")
@XmlRootElement
public class Province implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String acronym;

	@Column(nullable=false, length=40)
	private String name;

    public Province() {
    }

	public String getAcronym() {
		return this.acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acronym == null) ? 0 : acronym.hashCode());
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
		Province other = (Province) obj;
		if (acronym == null) {
			if (other.acronym != null)
				return false;
		} else if (!acronym.equals(other.acronym))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return acronym;
	}
	
}