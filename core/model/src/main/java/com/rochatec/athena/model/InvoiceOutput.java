package com.rochatec.athena.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "OUTPUT")
public class InvoiceOutput extends AbstractInvoice {

	/**
	 * 
	 */
	private static final long serialVersionUID = -98982323230368255L;

	/**
	 * Destinat�rio da Nota Fiscal
	 */
	@OneToOne
	@JoinColumn(name = "ISSUER", referencedColumnName = "ID")
	private Customer issuer;

	/**
	 * Emissor da Nota fiscal
	 */
	@OneToOne
	@JoinColumn(name = "RECEIVER", referencedColumnName = "ID")
	private Company receiver;

	/**
	 * Data em que a nota fiscal de Saída foi impressa
	 */
	@Column(name = "PRINT_DATE")
	private Calendar datePrinted;

	/**
	 * Data em que foi gerada, depois de gerada não pode ser alterada
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "MAKE_DATE")
	private Calendar makeDate;

	public Calendar getDatePrinted() {
		return datePrinted;
	}

	public void setDatePrinted(Calendar datePrinted) {
		this.datePrinted = datePrinted;
	}

	public Calendar getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Calendar makeDate) {
		this.makeDate = makeDate;
	}

	public Customer getIssuer() {
		return issuer;
	}

	public void setIssuer(Customer issuer) {
		this.issuer = issuer;
	}

	public Company getReceiver() {
		return receiver;
	}

	public void setReceiver(Company receiver) {
		this.receiver = receiver;
	}
	
}
