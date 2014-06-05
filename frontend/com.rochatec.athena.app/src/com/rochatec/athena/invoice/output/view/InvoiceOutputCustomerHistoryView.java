package com.rochatec.athena.invoice.output.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISharedImages;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.client.service.InvoiceClientService;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.InvoiceOutput;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.util.CommandFactory;
import com.rochatec.athena.util.ICommands;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.model.HierarchyObject;
import com.rochatec.graphics.provider.HierarchyContentProvider;
import com.rochatec.graphics.provider.HierarchyLabelProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.view.AbstractView;

public class InvoiceOutputCustomerHistoryView extends AbstractView implements ISelectionChangedListener{
	
	public static final String ID = "com.rochatec.athena.invoice.output.view.InvoiceOutputCustomerHistoryView";
	protected InvoiceClientService invoiceClientService = ServiceFactory.getInstance().getInvoiceClientService();
	
	protected TreeViewer viewer;
	private HierarchyObject root;
	
	public InvoiceOutputCustomerHistoryView() {
	}
	
	@Override
	protected void createContents(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getFillLayout());
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true,true));				
		viewer = new TreeViewer(composite);
		viewer.setContentProvider(new HierarchyContentProvider());
		viewer.setLabelProvider(new HierarchyLabelProvider());
		MenuManager menuManager = new MenuManager();
	    Menu menu = menuManager.createContextMenu(viewer.getTree());
	    viewer.getTree().setMenu(menu);
	    getSite().registerContextMenu(menuManager, viewer);
		getSite().setSelectionProvider(viewer);		
		viewer.addDoubleClickListener(CommandFactory.getDoubleClickCommand(ICommands.INVOICE_INPUT_EDIT, getSite()));
	}	
	
	@Override
	public void setFocus() {
		viewer.getTree().setFocus();		
	}
	
	public void add(Supplier supplier,InvoiceInput invoiceInput){
		if (root == null){
			root = new HierarchyObject(supplier.getCompanyName());
			root.setWrapper(supplier);
			root.setImage(Activator.getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER).createImage());
		}
		
		if (root != null && root.getChilds() == null){
			root.setChilds(new ArrayList<HierarchyObject>());
		}
				
		HierarchyObject object = new HierarchyObject(invoiceInput.toString(),root,invoiceInput);
		object.setImage(Activator.getImageDescriptor(IPathIcons.INVOICE_20).createImage());
		root.addChild(object);
		viewer.setInput(root);
	}
	
	private void fill(Customer customer, List<InvoiceOutput> invoices){
		root = new HierarchyObject(customer.getName());
		root.setImage(Activator.getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER).createImage());
		root.setWrapper(customer);
						
		for (InvoiceOutput invoice : invoices){
			HierarchyObject object = new HierarchyObject(invoice.toString(),root,invoice);
			object.setImage(Activator.getImageDescriptor(IPathIcons.INVOICE_20).createImage());
			root.addChild(object);			
		}
		viewer.setInput(root);		
	}
	
	public void addSelectionChangedListener(ISelectionChangedListener listener){
		this.viewer.addSelectionChangedListener(listener);
	}
	
	public void removeSelectionChangedListener(ISelectionChangedListener listener){
		this.viewer.removeSelectionChangedListener(listener);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		SearchSelection<Customer> selection = (SearchSelection<Customer>)event.getSelection();
		Customer customer = selection.getSingleSelection();
		HierarchyObject hierarchyObject = new HierarchyObject(customer.getName(),null,customer);
		viewer.setSelection(new SearchSelection<HierarchyObject>(hierarchyObject));
		List<InvoiceOutput> invoices = invoiceClientService.findAllInvoiceOutputstByReceiver(customer);
		fill(customer,invoices);
	}

	@Override
	protected void addListeners() {
		
	}	
}
