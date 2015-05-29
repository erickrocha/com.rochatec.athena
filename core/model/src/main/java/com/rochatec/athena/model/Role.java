package com.rochatec.athena.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ROLE")
@XmlRootElement
public class Role implements Serializable {

	private static final long serialVersionUID = -5186616697168139116L;

	@Id
	@Column(name = "KEY", updatable = false, unique = true,length=5)
	protected String key;

	@Column(name = "NAME", updatable = true, insertable = true, nullable = false,length=30)
	protected String name;

	@Column(name = "LABEL", updatable = true, insertable = true, nullable = true,length=50)
	protected String description;

	public Role() {
	}

	public Role(String key, String name, String description) {
		super();
		this.key = key;
		this.name = name;
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		Role other = (Role) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + ": " + description;
	}

}
