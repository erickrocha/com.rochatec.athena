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

	public List<InvoiceInputItem> getItems() {
		return items;
	}
}
