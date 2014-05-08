package com.rochatec.athena.invoice.input.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.client.service.InvoiceClientService;
import com.rochatec.athena.invoice.input.action.InvoiceInputEditAction;
import com.rochatec.athena.invoice.input.action.InvoiceInputNewAction;
import com.rochatec.athena.model.InvoiceInput;
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

public class InvoiceInputSupplierHistoryView extends AbstractView implements ISelectionChangedListener{
	
	public static final String ID = "com.rochatec.athena.invoice.input.view.InvoiceInputSupplierHistoryView";
	protected InvoiceClientService invoiceClientService = ServiceFactory.getInstance().getInvoiceClientService();
	
	protected TreeViewer viewer;
	private HierarchyObject root;
	
	public InvoiceInputSupplierHistoryView() {
	}
	
	@Override
	protected void createContents(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getFillLayout());
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL, true,true));				
		viewer = new TreeViewer(composite);
		viewer.setContentProvider(new HierarchyContentProvider());
		viewer.setLabelProvider(new HierarchyLabelProvider());		
		getSite().setSelectionProvider(viewer);
		viewer.addPostSelectionChangedListener(new InvoicePostSelectedListener());
		viewer.addDoubleClickListener(CommandFactory.getDoubleClickCommand(ICommands.INVOICE_INPUT_EDIT, getSite()));
	}
	
	@Override
	public void setFocus() {
		viewer.getTree().setFocus();		
	}
	
	public void add(Supplier supplier,InvoiceInput invoiceInput){
		root = new HierarchyObject(supplier.getCompanyName());
		root.setWrapper(supplier);
		root.setImage(Activator.getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER).createImage());
		
		HierarchyObject object = new HierarchyObject(invoiceInput.toString(),root,invoiceInput);
		object.setImage(Activator.getImageDescriptor(IPathIcons.INVOICE_20).createImage());
		root.addChild(object);
		viewer.setInput(root);
	}
	
	private void fill(Supplier supplier, List<InvoiceInput> invoices){
		root = new HierarchyObject(supplier.getCompanyName());
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
		createToolbarAdd(supplier);
	}
	
	private void createToolbarAdd(Supplier supplier){
		form.getToolBarManager().add(new InvoiceInputNewAction(this,supplier));		
		form.getForm().setToolBarVerticalAlignment(SWT.TOP);		
		form.getToolBarManager().update(true);		
	}
	
	private void createToolbarEdit(InvoiceInput invoice){
		form.getToolBarManager().add(new InvoiceInputEditAction(this,invoice));		
		form.getForm().setToolBarVerticalAlignment(SWT.TOP);		
		form.getToolBarManager().update(true);
	}

	@Override
	protected void addListeners() {
		
	}
	
	class InvoicePostSelectedListener implements ISelectionChangedListener{

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			HierarchyObject hierarchyObject = (HierarchyObject) ((ITreeSelection)event.getSelection()).getFirstElement();			
			if (hierarchyObject.getWrapper() instanceof InvoiceInput){
				InvoiceInput invoice = (InvoiceInput)hierarchyObject.getWrapper();
				createToolbarEdit(invoice);
			}
		}
		
	}
}
