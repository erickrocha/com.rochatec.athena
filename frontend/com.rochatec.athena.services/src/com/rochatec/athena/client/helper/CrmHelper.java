package com.rochatec.athena.client.helper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.model.SaleOrderItem;

public class CrmHelper {

	public void fill(SaleOrder saleOrder,List<SaleOrderItem> items){
		Set<SaleOrderItem> orderItems = new HashSet<SaleOrderItem>();
		if(items != null && !items.isEmpty()){
			for (SaleOrderItem item : items){
				item.setSaleOrder(saleOrder);
				orderItems.add(item);
			}
			saleOrder.setItems(orderItems);
		}
	}
	
}
