package com.rochatec.athena.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PROFILE",schema="METALLURGICAL")
@NamedQueries({
		@NamedQuery(name = "Profile.findByName", query = "SELECT p FROM Profile p WHERE p.label like :name ORDER BY p.name"),
		@NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p ORDER BY p.name") })
public class Profile implements Serializable {

	private static final long serialVersionUID = 3408937870202393757L;

	@Id
	@Column(name = "ID", updatable = false, unique = true)
	@SequenceGenerator(sequenceName="SEQ_PROFILE",name="SEQ_PROFILE",allocationSize=1,initialValue=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_PROFILE")
	protected Long id;

	@Column(name = "NAME", updatable = true, insertable = true)
	protected String name;
	
	@Column(name="LABEL",updatable =true,insertable =true)
	protected String label;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "PROFILE_ROLE",
			joinColumns = { 
					@JoinColumn(name = "PROFILE", nullable = false) 
					}
			, inverseJoinColumns = { 
					@JoinColumn(name = "ROLE", nullable = false) })
	protected Set<Role> roles;

	public Profile() {
	}

	public Profile(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getLabel() {
		return label != null ? this.label : "";
	}

	public void setLabel(String label) {
		this.label = label.trim();
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
		Profile other = (Profile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return label;
	}

}
