package com.rochatec.pos.athena.persistence.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID",unique=true)
	private Long id;
	
	@Column(name="NAME",insertable=true,updatable=true,nullable=false,unique=true,length=30)
	private String name;
	
	@Column(name="USERNAME",insertable=true,updatable=true,nullable=false,unique=true,length=30)	
	private String username;
	
	@Column(name="PASSWORD",insertable=true,updatable=true,nullable=false,unique=false,length=50)	
	private String password;
	
	@OneToOne
	@JoinColumn(name="PROFILE",referencedColumnName="ID")
	private Profile profile;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
		name="USER_ROLE"
		, joinColumns={
			@JoinColumn(name="USERS", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="ROLE", nullable=false)
			}
		)
	private Set<Role> roles;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS",insertable=true,updatable=true,nullable=false,unique=false)
	protected Status status;

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
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}	
	
}
