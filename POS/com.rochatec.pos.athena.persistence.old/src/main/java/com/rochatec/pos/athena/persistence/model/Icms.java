package com.rochatec.pos.athena.persistence.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ICMS database table.
 * 
 */
@Entity
@Table(name="ICMS")
@NamedQueries({
	@NamedQuery(name="Icms.findAll",query="SELECT i FROM Icms i ")
})
public class Icms implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=40)
	private String description;

	@Column(nullable=false, precision=6, scale=2)
	private BigDecimal percentage = BigDecimal.ZERO;

    public Icms() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPercentage() {
		return this.percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
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
		Icms other = (Icms) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return percentage.toString();
	}
	
}