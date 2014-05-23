package com.rochatec.athena.client.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.rochatec.athena.model.Icms;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceInputItem;
import com.rochatec.athena.model.ItemStatus;
import com.rochatec.athena.model.Product;

public class InvoiceInputHelper {

	private List<InvoiceInputItem> items = new ArrayList<InvoiceInputItem>();

	public InvoiceInputHelper(List<InvoiceInputItem> items) {
		this.items = items;
	}
	
	public void addItem(InvoiceInput invoice, Product product,
			Icms icms, BigDecimal costPrice, BigDecimal ipiBase,
			BigDecimal ipiValue,BigDecimal quantity){
		InvoiceInputItem item = new InvoiceInputItem();
		item.setInvoice(invoice);
		item.setProduct(product);
		item.setStatus(ItemStatus.OK);
		item.setIcms(icms);
		item.setQuantity(quantity);
		item.setIpiBase(ipiBase);
		item.setCostPrice(costPrice);
		item.setIpiValue(ipiValue);
		addItem(item);
	}
	
	public void addItem(InvoiceInputItem invoiceInputItem){
		if (this.items == null)
			this.items = new ArrayList<InvoiceInputItem>();
		this.items.add(invoiceInputItem);
	}

	public InvoiceInputItem newItem(InvoiceInput invoice, Product product,
			Icms icms, BigDecimal costPrice, BigDecimal ipiBase,
			BigDecimal ipiValue,BigDecimal quantity) {
		InvoiceInputItem item = new InvoiceInputItem();
		item.setInvoice(invoice);
		item.setProduct(product);
		item.setStatus(ItemStatus.OK);
		item.setIcms(icms);
		item.setQuantity(quantity);
		item.setIpiBase(ipiBase);
		item.setCostPrice(costPrice);
		item.setIpiValue(ipiValue);
		return item;
	}
	
	public InvoiceInputItem copy(InvoiceInputItem inputItem){
		InvoiceInputItem item = new InvoiceInputItem();
		item.setInvoice(inputItem.getInvoice());
		item.setCostPrice(inputItem.getCostPrice());
		item.setIcms(inputItem.getIcms());
		item.setIpiBase(inputItem.getIpiBase());
		item.setIpiValue(inputItem.getIpiValue());
		item.setProduct(inputItem.getProduct());
		item.setQuantity(inputItem.getQuantity());
		item.setStatus(inputItem.getStatus());
		item.setTotalProduct(inputItem.getTotalItems());
		return item;
	}
	
	public InvoiceInputItem getByIndex(int index){
		return this.items.get(index);
	}
	
	public InvoiceInputItem getByProduct(Product product){
		for (InvoiceInputItem item : this.items){
			if (item.getProduct().equals(product)){
				return item;
			}
		}
		return null;
	}
	
	public boolean deleteItem(InvoiceInputItem item){
		if (this.items.contains(item)){
			return this.items.remove(item);
		}
		return false;
	}
	
	public void updateItem(InvoiceInput invoice, Product product,
			Icms icms, BigDecimal costPrice, BigDecimal ipiBase,
			BigDecimal ipiValue,BigDecimal quantity){
		for (InvoiceInputItem item : this.items){
			if (item.getProduct().equals(product) && item.getInvoice().equals(invoice)){
				item.setIcms(icms);
				item.setCostPrice(costPrice);
				item.setIpiBase(ipiBase);
				item.setIpiValue(ipiValue);
				item.setQuantity(quantity);
			}
		}
	}
	
	public BigDecimal sumItems(){
		BigDecimal total = BigDecimal.ZERO;
		for (InvoiceInputItem item : this.items){
			total.add(item.getTotalItems());
		}
		return total;
	}

	public List<InvoiceInputItem> getItems() {
		return items;
	}
}
