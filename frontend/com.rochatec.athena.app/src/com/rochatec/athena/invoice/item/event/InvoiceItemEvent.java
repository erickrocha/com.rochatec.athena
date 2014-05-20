package com.rochatec.athena.invoice.item.event;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Text;

import com.rochatec.athena.model.Icms;
import com.rochatec.athena.model.InvoiceInputItem;
import com.rochatec.athena.model.Product;

public class InvoiceItemEvent extends EventObject{

	public Text quantity;
	public Product product;
	public Text costPrice;
	public Icms icms;
	public Text ipi;
	public Text ipiBase;
	
	public InvoiceInputItem newItem;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2404577897613247843L;

	
	public InvoiceItemEvent(Object source) {
		super(source);
		this.product = (Product)source;
	}

	public Map<String,Object> toValues(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("quantity",quantity.getText());
		map.put("product",product);
		map.put("costPrice",costPrice.getText());
		map.put("icms",icms);
		map.put("ipi",ipi.getText());
		map.put("ipiBase",ipiBase.getText());
		return map;
	}
	
	
}
