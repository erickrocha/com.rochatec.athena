package com.rochatec.athena.supply.supplier.view;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.supply.supplier.provider.SupplierLabelProvider;
import com.rochatec.athena.supply.supplier.table.SupplierTable;
import com.rochatec.athena.util.CommandFactory;
import com.rochatec.athena.util.ICommands;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.table.AbstractTable;
import com.rochatec.graphics.view.AbstractView;

public class SupplierTableView extends AbstractView implements ISelectionChangedListener{
	
	public static final String ID = "com.rochatec.athena.supply.supplier.view.SupplierTableView";
	protected AbstractTable tableViewer;
	
	@Override
	protected void createContents(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		tableViewer = new SupplierTable(composite);		
		tableViewer.setContentProvider(new GenericContentProvider<Supplier>());
		tableViewer.setLabelProvider(new SupplierLabelProvider());
		MenuManager menuManager = new MenuManager();
	    Menu menu = menuManager.createContextMenu(tableViewer.getTable());
	    tableViewer.getTable().setMenu(menu);
	    getSite().registerContextMenu(menuManager, tableViewer.getViewer());
		getSite().setSelectionProvider(tableViewer.getViewer());
		tableViewer.addDoubleClickListener(CommandFactory.getDoubleClickCommand(ICommands.SUPPLIER_EDIT,getSite()));
	}

	@Override
	protected void addListeners() {
		
	}

	@Override
	public void setFocus() {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		SearchSelection<Supplier> selection = (SearchSelection<Supplier>)event.getSelection();
		tableViewer.setInput(selection.toList());		
	}

}
