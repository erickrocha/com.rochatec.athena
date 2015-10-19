package com.rochatec.athena.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value="PRODUCT")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product extends AbstractProduct{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3279494255517340948L;

	
}
