package com.rochatec.athena.salesOrder.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.salesOrder.view.SaleOrderHistoryView;

public class SaleOrderNewHandler extends DefaultCrudHandler{
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ITreeSelection selection =  (ITreeSelection)HandlerUtil.getCurrentSelection(event);		
		Customer customer = (Customer)selection.getFirstElement();
		
		SaleOrder saleOrder = new  SaleOrder();
		saleOrder.setCustomer(customer);
		
		SaleOrderHistoryView saleOrderHistoryView = (SaleOrderHistoryView) HandlerUtil.getActivePart(event);
		   saleOrderHistoryView.getSite().getSelectionProvider().getSelection();
		return super.execute(event);
	}
	
	
	
}
