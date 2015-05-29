package com.rochatec.athena.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "INVOICE")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "INVOICE_TYPE")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractInvoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4069843333891983862L;

	@Id
	@SequenceGenerator(name = "SEQ_INVOICE", sequenceName = "SEQ_INVOICE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INVOICE")
	@Column(unique = true, nullable = false)
	private Long id;

	/**
	 * Data de emissão da Nota fiscal de Entrada. Data em que a nota fiscal de
	 * Saída foi Salva, nessa data ainda é possivel alterar
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_REGISTER")
	private Calendar dateRegister;

	/**
	 * Número da Nota Fiscal
	 */
	@Column(name = "INVOICE_NUMBER")
	private String number;

	/**
	 * Número de Série para controle de formulário
	 */
	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;

	/**
	 * CFOP da Nota fiscal
	 */
	@Column(name = "CFOP")
	private String cfop;

	/**
	 * Natureza da operação da Nota
	 */
	@Column(name = "NATURE_OF_OPERATION")
	private String natureOfOperation;		

	/**
	 * Valores da Nota fiscal, Abstraido em um objeto
	 */
	@Embedded
	private InvoiceValue values;

	/**
	 * Transportadora da Nota fiscal
	 */
	@OneToOne
	@JoinColumn(name="SHIPPER",referencedColumnName="ID")
	private Shipper shipper;

	/**
	 * Status da Nota Fiscal
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "INVOICE_STATUS")
	private InvoiceStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public String getNatureOfOperation() {
		return natureOfOperation;
	}

	public void setNatureOfOperation(String natureOfOperation) {
		this.natureOfOperation = natureOfOperation;
	}

	public InvoiceValue getValues() {
		return values;
	}

	public void setValues(InvoiceValue values) {
		this.values = values;
	}

	public Shipper getShipper() {
		return shipper;
	}

	public void setShipper(Shipper shipper) {
		this.shipper = shipper;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public void setStatus(InvoiceStatus status) {
		this.status = status;
	}

	public Calendar getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Calendar dateRegister) {
		this.dateRegister = dateRegister;
	}

	@Override
	public String toString() {
		return "NF: " + number.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		AbstractInvoice other = (AbstractInvoice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	
	
}
