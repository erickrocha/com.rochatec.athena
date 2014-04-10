package com.rochatec.athena.manufacture.product.view;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import com.rochatec.athena.manufacture.product.provider.ProductLabelProvider;
import com.rochatec.athena.manufacture.product.table.ProductTable;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.util.CommandFactory;
import com.rochatec.athena.util.ICommands;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.table.AbstractTable;
import com.rochatec.graphics.view.AbstractView;

public class ProductTableView extends AbstractView implements ISelectionChangedListener{
	
	public static final String ID ="com.rochatec.athena.manufacture.product.view.ProductTableView";
	protected AbstractTable tableViewer;
	
	@Override
	protected void createContents(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		tableViewer = new ProductTable(composite);		
		tableViewer.setContentProvider(new GenericContentProvider<Product>());
		tableViewer.setLabelProvider(new ProductLabelProvider());
		MenuManager menuManager = new MenuManager();
	    Menu menu = menuManager.createContextMenu(tableViewer.getTable());
	    tableViewer.getTable().setMenu(menu);
	    getSite().registerContextMenu(menuManager, tableViewer.getViewer());
		getSite().setSelectionProvider(tableViewer.getViewer());
		tableViewer.addDoubleClickListener(CommandFactory.getDoubleClickCommand(ICommands.PRODUCT_EDIT,getSite()));
		
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
		SearchSelection<Product> selection = (SearchSelection<Product>)event.getSelection();
		tableViewer.setInput(selection.toList());		
	}

}
