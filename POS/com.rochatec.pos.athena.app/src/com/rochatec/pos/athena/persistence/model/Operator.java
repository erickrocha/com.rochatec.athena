package com.rochatec.pos.athena.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OPERATOR")
public class Operator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "KEY", length = 10)
	private String key;

	@Column(name = "PASSWORD", length = 50)
	private String password;

	@Column(name = "NAME", length = 300)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", length = 20)
	private Status status;

	@Enumerated(EnumType.STRING)
	@Column(name = "HIERARCHY", length = 50)
	private Hierarchy hierarchy;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Hierarchy getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Hierarchy hierarchy) {
		this.hierarchy = hierarchy;
	}

}
