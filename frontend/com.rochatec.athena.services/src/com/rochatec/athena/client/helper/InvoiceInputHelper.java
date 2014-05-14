package com.rochatec.athena.client.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceInputItem;

public class InvoiceInputHelper {
	
	private List<InvoiceInputItem> items = new ArrayList<InvoiceInputItem>();
	
	public InvoiceInputHelper(List<InvoiceInputItem> items) {
		this.items = items;
	}
	
	public InvoiceInputItem newItem(InvoiceInput invoice,Map<String,Object> values){
		return null;
	}
	
	public List<InvoiceInputItem> getItems(){
		return items;
	}
}
