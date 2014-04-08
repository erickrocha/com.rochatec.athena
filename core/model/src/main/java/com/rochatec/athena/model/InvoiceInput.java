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

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "INPUT")
public class InvoiceInput extends AbstractInvoice {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2323029811648538763L;

	/**
	 * Emissor da Nota fiscal
	 */
	@OneToOne
	@JoinColumn(name="ISSUER",referencedColumnName="ID")
	private Supplier issuer;

	/**
	 * Destinatário da Nota Fiscal
	 */
	@OneToOne
	@JoinColumn(name="RECEIVER",referencedColumnName="ID")
	private Company receiver;

	/**
	 * Data de chegada da Nota fiscal
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "MAKE_DATE")
	private Calendar arrivalDate;
	
	@OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InvoiceInputItem> items;

	public Calendar getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Calendar arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Supplier getIssuer() {
		return issuer;
	}

	public void setIssuer(Supplier issuer) {
		this.issuer = issuer;
	}

	public Company getReceiver() {
		return receiver;
	}

	public void setReceiver(Company receiver) {
		this.receiver = receiver;
	}

	public Set<InvoiceInputItem> getItems() {
		return items;
	}

	public void setItems(Set<InvoiceInputItem> items) {
		this.items = items;
	}
	
	public List<InvoiceInputItem> getItemsList(){
		return new ArrayList<InvoiceInputItem>(this.items);
	}
	
	public void setItemsList(List<InvoiceInputItem> items){
		this.items = new HashSet<InvoiceInputItem>(items);
	}
	
	public List<IProductItem> getProductItems(){
		List<IProductItem> items = new ArrayList<IProductItem>();
		for (InvoiceInputItem item : getItems()){
			items.add(item);
		}
		return items;
	}
	
	public void addItem(InvoiceInputItem newItem){
		if (this.items.contains(newItem)){
			Iterator<InvoiceInputItem> iterator = this.items.iterator();
			while (iterator.hasNext()){
				InvoiceInputItem oldItem = iterator.next();
				oldItem.setQuantity(oldItem.getQuantity().add(newItem.getQuantity()));
			}
		}else{
			newItem.setInvoice(this);
			this.items.add(newItem);
		}
		
	}

}
