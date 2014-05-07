package com.rochatec.athena.salesOrder.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.client.service.SalesClientService;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.salesOrder.view.SaleOrderHistoryView;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.model.TreeParent;
import com.rochatec.graphics.selection.DefaultSelectHandler;

public class SaleOrderNewHandler extends DefaultSelectHandler {
	
	private Customer customer;
	private SaleOrderHistoryView saleOrderHistoryView;
	private SalesClientService salesClientService = ServiceFactory.getInstance().getSalesClientService();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ITreeSelection selection =  (ITreeSelection)HandlerUtil.getCurrentSelection(event);		
		TreeParent root = (TreeParent)selection.getFirstElement();
		customer = (Customer)root.getObject();
		return null;
	}

	
	
}
