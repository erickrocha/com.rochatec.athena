package com.rochatec.pos.athena.persistence.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BOX")
public class Box implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPEN_DATE")
	private Calendar openDate;

	@Column(name = "PRINTER_SERIAL_NUMBER", length = 30)
	private String printerSerialNumber;

	@Column(name = "ECF_NUMBER")
	private String ecf;

	@OneToOne
	@JoinColumn(name = "OPERATOR", referencedColumnName = "KEY")
	private Operator operator;

	@Column(name = "INITIAL_COUNTER", length = 30)
	private String initialCounter;

	@Column(name = "FINISH_COUNTER", length = 30)
	private String finishCounter;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FINISH_DATE")
	private Calendar finishDate;

	@OneToOne
	@JoinColumn(name = "AUTORIZED_BY", referencedColumnName = "KEY")
	private Operator autorizedBy;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private BoxStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Calendar openDate) {
		this.openDate = openDate;
	}

	public String getPrinterSerialNumber() {
		return printerSerialNumber;
	}

	public void setPrinterSerialNumber(String printerSerialNumber) {
		this.printerSerialNumber = printerSerialNumber;
	}

	public String getEcf() {
		return ecf;
	}

	public void setEcf(String ecf) {
		this.ecf = ecf;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getInitialCounter() {
		return initialCounter;
	}

	public void setInitialCounter(String initialCounter) {
		this.initialCounter = initialCounter;
	}

	public String getFinishCounter() {
		return finishCounter;
	}

	public void setFinishCounter(String finishCounter) {
		this.finishCounter = finishCounter;
	}

	public Calendar getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Calendar finishDate) {
		this.finishDate = finishDate;
	}

	public Operator getAutorizedBy() {
		return autorizedBy;
	}

	public void setAutorizedBy(Operator autorizedBy) {
		this.autorizedBy = autorizedBy;
	}

	public BoxStatus getStatus() {
		return status;
	}

	public void setStatus(BoxStatus status) {
		this.status = status;
	}
	
	public boolean isOpen(){
		return this.status.equals(BoxStatus.OPEN)? true : false;
	}
	
	public boolean isClosed(){
		return this.status.equals(BoxStatus.CLOSED)? true : false;
	}
	
	public boolean isPartialClosed() {
		return this.status.equals(BoxStatus.PARTIAL_CLOSED)? true : false;
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
		Box other = (Box) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
