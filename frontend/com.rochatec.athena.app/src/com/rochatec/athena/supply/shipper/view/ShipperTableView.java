package com.rochatec.athena.supply.shipper.view;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import com.rochatec.athena.model.Shipper;
import com.rochatec.athena.supply.shipper.provider.ShipperLabelProvider;
import com.rochatec.athena.supply.shipper.table.ShipperTable;
import com.rochatec.athena.util.CommandFactory;
import com.rochatec.athena.util.ICommands;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.table.AbstractTable;
import com.rochatec.graphics.view.AbstractView;

public class ShipperTableView extends AbstractView implements ISelectionChangedListener{
	
	public static final String ID = "com.rochatec.athena.supply.shipper.view.ShipperTableView";
	protected AbstractTable table;
	
	@Override
	protected void createContents(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		table = new ShipperTable(composite);		
		table.setContentProvider(new GenericContentProvider<Shipper>());
		table.setLabelProvider(new ShipperLabelProvider());
		MenuManager menuManager = new MenuManager();
	    Menu menu = menuManager.createContextMenu(table.getTable());
	    table.getTable().setMenu(menu);
	    getSite().registerContextMenu(menuManager, table.getViewer());
		getSite().setSelectionProvider(table.getViewer());
		table.addDoubleClickListener(CommandFactory.getDoubleClickCommand(ICommands.SHIPPER_EDIT,getSite()));
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
		SearchSelection<Shipper> selection = (SearchSelection<Shipper>)event.getSelection();
		table.setInput(selection.toList());	
	}

}
