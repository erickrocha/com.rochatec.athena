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
	
	public InvoiceInputItem merge(InvoiceInputItem oldItem,InvoiceInputItem newItem){
		oldItem.setCostPrice(newItem.getCostPrice());
		oldItem.setQuantity(newItem.getQuantity());
		oldItem.setIpiBase(newItem.getIpiBase());
		oldItem.setIcms(newItem.getIcms());
		oldItem.setTotalProduct(newItem.getTotalItems());
		return oldItem;
	}

	public List<InvoiceInputItem> getItems() {
		return items;
	}
}
