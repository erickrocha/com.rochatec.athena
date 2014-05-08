package com.rochatec.athena.invoice.input.view;

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
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.util.CommandFactory;
import com.rochatec.athena.util.ICommands;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.model.HierarchyObject;
import com.rochatec.framework.model.TreeParent;
import com.rochatec.graphics.provider.HierarchyContentProvider;
import com.rochatec.graphics.provider.HierarchyLabelProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.view.AbstractView;

public class InvoiceInputSupplierHistoryView extends AbstractView implements ISelectionChangedListener{
	
	public static final String ID = "com.rochatec.athena.invoice.input.view.InvoiceInputSupplierHistoryView";
	protected InvoiceClientService invoiceClientService = ServiceFactory.getInstance().getInvoiceClientService();
	
	protected TreeViewer viewer;
	private TreeParent root;
	private TreeParent invoiceGroup;
	private TreeParent purchaseOrderGroup;
	
	public InvoiceInputSupplierHistoryView() {
		root = new TreeParent("",0);
		invoiceGroup = new TreeParent(Messages.getMessage("invoice"),1);
		purchaseOrderGroup = new TreeParent(Messages.getMessage("purchase.order"),2);
		root.addChild(invoiceGroup);
		root.addChild(purchaseOrderGroup);
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
		getSite().setSelectionProvider(viewer);
		getSite().registerContextMenu(menuManager,viewer);
		viewer.addDoubleClickListener(CommandFactory.getDoubleClickCommand(ICommands.INVOICE_INPUT_EDIT, getSite()));
	}
	
	@Override
	public void setFocus() {
		viewer.getTree().setFocus();		
	}
	
	public void add(Supplier supplier,InvoiceInput invoiceInput){
		HierarchyObject root = new HierarchyObject(supplier.getCompanyName());
		root.setWrapper(supplier);
		root.setImage(Activator.getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER).createImage());
		
		HierarchyObject object = new HierarchyObject(invoiceInput.toString(),root,invoiceInput);
		object.setImage(Activator.getImageDescriptor(IPathIcons.INVOICE_20).createImage());
		root.addChild(object);
		viewer.setInput(root);
	}
	
	private void fill(Supplier supplier, List<InvoiceInput> invoices){
		HierarchyObject root = new HierarchyObject(supplier.getCompanyName());
		root.setImage(Activator.getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER).createImage());
		root.setWrapper(supplier);
						
		for (InvoiceInput invoice : invoices){
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
		SearchSelection<Supplier> selection = (SearchSelection<Supplier>)event.getSelection();
		Supplier supplier = selection.getSingleSelection();
		List<InvoiceInput> invoices = invoiceClientService.findAllInvoiceInputByIssuer(supplier);
		fill(supplier,invoices);
	}

	@Override
	protected void addListeners() {
		
	}

}
