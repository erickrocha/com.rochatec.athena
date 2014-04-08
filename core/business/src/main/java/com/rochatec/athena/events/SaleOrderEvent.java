package com.rochatec.athena.events;

import java.util.EventObject;

import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.model.SaleOrderStatus;

public class SaleOrderEvent extends EventObject{

	public SaleOrder saleOrder;
	public SaleOrderStatus action;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1217413319381864632L;
	
	public SaleOrderEvent(Object source) {
		super(source);
	}
	
}
