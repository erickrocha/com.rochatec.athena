package com.rochatec.athena.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rochatec.metallurgical.util.CalendarUtil;

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
	private Company issuer;

	/**
	 * Emissor da Nota fiscal
	 */
	@OneToOne
	@JoinColumn(name = "RECEIVER", referencedColumnName = "ID")
	private Customer receiver;

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

	public Company getIssuer() {
		return issuer;
	}

	public void setIssuer(Company issuer) {
		this.issuer = issuer;
	}

	public Customer getReceiver() {
		return receiver;
	}

	public void setReceiver(Customer receiver) {
		this.receiver = receiver;
	}
	
	@OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InvoiceOutputItem> items;
	
	
	public Set<InvoiceOutputItem> getItems() {
		return items;
	}

	public void setItems(Set<InvoiceOutputItem> items) {
		this.items = items;
	}
	
	public List<InvoiceOutputItem> getItemsList(){
		if (items != null)
			return new ArrayList<InvoiceOutputItem>(this.items);
		return new ArrayList<InvoiceOutputItem>();
	}
	
	public void setItemsList(List<InvoiceOutputItem> items){
		this.items = new HashSet<InvoiceOutputItem>(items);
	}
	
	public void addItem(InvoiceOutputItem newItem){
		if (this.items.contains(newItem)){
			Iterator<InvoiceOutputItem> iterator = this.items.iterator();
			while (iterator.hasNext()){
				InvoiceOutputItem oldItem = iterator.next();
				oldItem.setQuantity(oldItem.getQuantity().add(newItem.getQuantity()));
			}
		}else{
			newItem.setInvoice(this);
			this.items.add(newItem);
		}
		
	}
	
	public static final InvoiceOutput factory(Company company,Customer customer){
		InvoiceOutput output = new InvoiceOutput();
		output.setDateRegister(CalendarUtil.getToday());
		output.setIssuer(company);
		output.setReceiver(customer);
		output.setValues(new InvoiceValue());
		return output;
	}
	
}
