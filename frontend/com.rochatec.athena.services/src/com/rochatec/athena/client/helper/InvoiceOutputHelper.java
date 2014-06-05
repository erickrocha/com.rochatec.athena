package com.rochatec.athena.client.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rochatec.athena.model.Icms;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceInputItem;
import com.rochatec.athena.model.InvoiceOutput;
import com.rochatec.athena.model.InvoiceOutputItem;
import com.rochatec.athena.model.InvoiceValue;
import com.rochatec.athena.model.ItemStatus;
import com.rochatec.athena.model.Product;

public class InvoiceOutputHelper {
	
	private List<InvoiceOutputItem> items = new ArrayList<InvoiceOutputItem>();

	public InvoiceOutputHelper(List<InvoiceOutputItem> items) {
		this.items = items;
	}
	
	public void addItem(InvoiceOutput invoice, Product product,
			Icms icms, BigDecimal costPrice, BigDecimal ipiBase,
			BigDecimal ipiValue,BigDecimal quantity){
		InvoiceOutputItem item = new InvoiceOutputItem();
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
	
	public void addItem(InvoiceOutputItem InvoiceOutputItem){
		if (this.items == null)
			this.items = new ArrayList<InvoiceOutputItem>();
		this.items.add(InvoiceOutputItem);
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
	
	public InvoiceOutputItem getByIndex(int index){
		return this.items.get(index);
	}
	
	public InvoiceOutputItem getByProduct(Product product){
		for (InvoiceOutputItem item : this.items){
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
	
	public void updateItem(InvoiceOutput invoice, Product product,
			Icms icms, BigDecimal costPrice, BigDecimal ipiBase,
			BigDecimal ipiValue,BigDecimal quantity){
		for (InvoiceOutputItem item : this.items){
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
		for (InvoiceOutputItem item : this.items){
			total.add(item.getTotalItems());
		}
		return total;
	}
	
	public Map<Integer,BigDecimal> getTotais(InvoiceValue value){
		Map<Integer,BigDecimal> values = new HashMap<Integer,BigDecimal>();
		BigDecimal totalIcms = BigDecimal.ZERO;
		BigDecimal totalIcmsSub = BigDecimal.ZERO;
		BigDecimal totalIpi = BigDecimal.ZERO;
		BigDecimal totalItem = BigDecimal.ZERO;
		BigDecimal totalInvoice = BigDecimal.ZERO;
		
		for (InvoiceOutputItem item : items){
			totalIcms = totalIcms.add(item.getTotalIcms());
			values.put(0,totalIcms);
			totalIcmsSub = totalIcmsSub.add(value.getTotalIcmsSub());
			values.put(1,totalIcmsSub);
			totalIpi = totalIpi.add(item.getIpiValue());
			values.put(2,totalIpi);
			totalItem = totalItem.add(item.getTotalItems());
			values.put(3,totalItem);
		}
		
		totalInvoice.add(value.getTotalItems()).add(value.getTotalIcmsSub()).add(value.getOutrasdespesas());
		values.put(4,totalInvoice);
		return values;
	}

	public List<InvoiceOutputItem> getItems() {
		return items;
	}
}
