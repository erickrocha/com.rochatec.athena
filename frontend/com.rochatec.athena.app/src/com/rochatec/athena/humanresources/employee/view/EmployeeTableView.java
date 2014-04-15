package com.rochatec.athena.humanresources.employee.view;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import com.rochatec.athena.humanresources.employee.provider.EmployeeLabelProvider;
import com.rochatec.athena.humanresources.employee.table.EmployeeTable;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.util.CommandFactory;
import com.rochatec.athena.util.ICommands;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.table.AbstractTable;
import com.rochatec.graphics.view.AbstractView;

public class EmployeeTableView extends AbstractView implements ISelectionChangedListener{
	
	public static final String ID = "com.rochatec.athena.humanresources.employee.view.EmployeeTableView";
	
	protected AbstractTable tableViewer; 
	
	@Override
	protected void createContents(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		
		tableViewer = new EmployeeTable(composite);		
		tableViewer.setContentProvider(new GenericContentProvider<Employee>());
		tableViewer.setLabelProvider(new EmployeeLabelProvider());
		MenuManager menuManager = new MenuManager();
	    Menu menu = menuManager.createContextMenu(tableViewer.getTable());
	    tableViewer.getTable().setMenu(menu);
	    getSite().registerContextMenu(menuManager, tableViewer.getViewer());
		getSite().setSelectionProvider(tableViewer.getViewer());
		tableViewer.addDoubleClickListener(CommandFactory.getDoubleClickCommand(ICommands.EMPLOYEE_EDIT,getSite()));
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
		SearchSelection<Employee> selection = (SearchSelection<Employee>)event.getSelection();
		tableViewer.setInput(selection.toList());		
		
	}

}
