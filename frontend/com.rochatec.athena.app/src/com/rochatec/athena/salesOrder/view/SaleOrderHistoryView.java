package com.rochatec.athena.salesOrder.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.client.service.SalesClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.model.SaleOrderStatus;
import com.rochatec.athena.util.CommandFactory;
import com.rochatec.athena.util.ICommands;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.model.TreeObject;
import com.rochatec.framework.model.TreeParent;
import com.rochatec.graphics.gui.TreePropertieViewer;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.view.AbstractView;

public class SaleOrderHistoryView extends AbstractView implements ISelectionChangedListener{
	
	public static final String ID = "com.rochatec.athena.salesOrder.view.SaleOrderHistoryView";
	protected SalesClientService salesClientService = ServiceFactory.getInstance().getSalesClientService();
	
	protected TreePropertieViewer viewer;
	
	@Override
	protected void createContents(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getFillLayout());
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true,true));
		viewer = new TreePropertieViewer(composite);
		getSite().setSelectionProvider(viewer.getViewer());
		viewer.addDoubleClickListener(CommandFactory.getDoubleClickCommand(ICommands.SALE_ORDER_EDIT, getSite()));
		
	}

	@Override
	protected void addListeners() {
		
	}

	@Override
	public void setFocus() {
		
	}
	
	private void fill(List<SaleOrder> saleOrders){
		TreeParent root = new TreeParent(Messages.getMessage("saleOrder.name"),0);				
		for (SaleOrder saleOrder : saleOrders){
			TreeObject object = new TreeObject(saleOrder.toStringwithDateRegister().toString(),1,saleOrder);
			object.setParent(root);
			root.addChild(object);
		}
		viewer.setInput(root);	
	}

	@SuppressWarnings("unchecked")
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		SearchSelection<Customer> selection = (SearchSelection<Customer>)event.getSelection();
		Customer customer = selection.getSingleSelection();
		List<SaleOrder> saleOrders = salesClientService.findSaleOrdersByCustomer(customer,null,null,SaleOrderStatus.ALL);
		fill(saleOrders);		
	}

}
