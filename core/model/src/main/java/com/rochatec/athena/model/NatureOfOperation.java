package com.rochatec.athena.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the NATURE_OF_OPERATION database table.
 * 
 */
@Entity
@Table(name="NATURE_OF_OPERATION")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NatureOfOperation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=20)
	private String cfop;

	@Column(nullable=false, length=60)
	private String application;

	@Column(name="LABEL", nullable=false, length=60)
	private String label;
	
	@ManyToOne
	@JoinColumn(name = "parent")
    @XmlElement
	private NatureOfOperation parent;

	@OneToMany(mappedBy = "parent",fetch=FetchType.EAGER)
	private List<NatureOfOperation> childs;

    public NatureOfOperation() {
    }

	public String getCfop() {
		return this.cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public String getApplication() {
		return this.application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public NatureOfOperation getParent() {
		return parent;
	}

	public void setParent(NatureOfOperation parent) {
		this.parent = parent;
	}

	public List<NatureOfOperation> getChilds() {
		return childs;
	}

	public void setChilds(List<NatureOfOperation> childs) {
		this.childs = childs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cfop == null) ? 0 : cfop.hashCode());
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
		NatureOfOperation other = (NatureOfOperation) obj;
		if (cfop == null) {
			if (other.cfop != null)
				return false;
		} else if (!cfop.equals(other.cfop))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return cfop+" - "+application;
	}

}