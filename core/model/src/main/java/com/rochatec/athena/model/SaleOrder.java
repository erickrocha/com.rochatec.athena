package com.rochatec.athena.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rochatec.metallurgical.util.CalendarUtil;

@Entity
@Table(name = "SALE_ORDER")
public class SaleOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2719809192985926688L;

	@Id
	@SequenceGenerator(name = "SEQ_SALES_ORDER", sequenceName = "SEQ_SALES_ORDER", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SALES_ORDER")
	@Column(unique = true, nullable = false)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMER", referencedColumnName = "ID")
	private Customer customer;

	@Column(name = "DATE_REGISTER")
	@Temporal(TemporalType.DATE)
	private Calendar dateRegister;

	@Column(name = "DATE_VALIDITY")
	@Temporal(TemporalType.DATE)
	private Calendar dateValidity;

	@Column(name = "DATE_DELIVERY")
	@Temporal(TemporalType.DATE)
	private Calendar dateDelivery;

	@Column(name = "TOTAL_ORDER", length = 10, scale = 10, precision = 2)
	private BigDecimal totalOrder;

	@OneToOne
	@JoinColumn(name = "EMPLOYEE", referencedColumnName = "ID")
	private Employee employee;

	@Column(name = "OBSERVATION", length = 600)
	private String observation;

	@OneToMany(mappedBy = "saleOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SaleOrderItem> items;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_GENERATE")
	private Calendar dateGenerate;
	
	@Column(name="STATUS",length=20)
	@Enumerated(EnumType.STRING)
	private SaleOrderStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Calendar getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Calendar dateRegister) {
		this.dateRegister = dateRegister;
	}

	public Calendar getDateValidity() {
		return dateValidity;
	}

	public void setDateValidity(Calendar dateValidity) {
		this.dateValidity = dateValidity;
	}

	public Calendar getDateDelivery() {
		return dateDelivery;
	}

	public void setDateDelivery(Calendar dateDelivery) {
		this.dateDelivery = dateDelivery;
	}

	public BigDecimal getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(BigDecimal totalOrder) {
		this.totalOrder = totalOrder;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Set<SaleOrderItem> getItems() {
		return items;
	}

	public void setItems(Set<SaleOrderItem> items) {
		this.items = items;
	}
	
	public void setItemList(List<SaleOrderItem> items){
		this.items = new HashSet<SaleOrderItem>(items);
	}
	
	public List<SaleOrderItem> getItemList(){
		return new ArrayList<SaleOrderItem>(this.items);
	}

	public SaleOrderStatus getStatus() {
		return status;
	}

	public void setStatus(SaleOrderStatus status) {
		this.status = status;
	}

	public Calendar getDateGenerate() {
		return dateGenerate;
	}

	public void setDateGenerate(Calendar dateGenerate) {
		this.dateGenerate = dateGenerate;
	}
	
	public BigDecimal getTotalItems(){
		BigDecimal total = BigDecimal.ZERO;
		total.setScale(2);
		for (SaleOrderItem item : items){
			total = total.add(item.getTotalItem());
		}
		return total;
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
		SaleOrder other = (SaleOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + ":" + customer.getName();
	}
	
	public String toStringwithDateRegister(){
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return "Nº: "+id.toString()+" Date:"+df.format(dateRegister.getTime());
	}
	
	public void addItem(SaleOrderItem item){
		if (this.items.contains(item)){
			Iterator<SaleOrderItem> iterator = this.items.iterator();
			while (iterator.hasNext()){
				SaleOrderItem oldItem = iterator.next();
				oldItem.setQuantity(oldItem.getQuantity().add(item.getQuantity()));
			}
		}else{
			item.setSaleOrder(this);
			this.items.add(item);
		}
	}
	
	public boolean removeItem(SaleOrderItem item){
		return this.items.remove(item);		
	}
	
	public List<IProductItem> getProducts(){
		List<IProductItem> items = new ArrayList<IProductItem>();
		for (SaleOrderItem item : getItemList()){			
			items.add(item);
		}
		return items;
	}
	
	public static final SaleOrder factory(Customer customer,Employee employee){
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setCustomer(customer);
		saleOrder.setEmployee(employee);
		saleOrder.setDateRegister(CalendarUtil.getToday());
		saleOrder.setStatus(SaleOrderStatus.SAVE);
		return saleOrder;
	}
 
}
