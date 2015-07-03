package com.rochatec.pos.athena.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private Long id;

	@Column(name="NAME")
	private String name;
	
	@OneToMany(mappedBy="category",fetch=FetchType.LAZY)
	private List<Product> products;

}
