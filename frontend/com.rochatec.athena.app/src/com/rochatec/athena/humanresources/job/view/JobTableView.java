package com.rochatec.athena.humanresources.job.view;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import com.rochatec.athena.humanresources.job.provider.JobLabelProvider;
import com.rochatec.athena.humanresources.job.table.JobTable;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Job;
import com.rochatec.athena.util.CommandFactory;
import com.rochatec.athena.util.ICommands;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.table.AbstractTable;
import com.rochatec.graphics.view.AbstractView;

public class JobTableView extends AbstractView implements ISelectionChangedListener{
	
	public static final String ID = "com.rochatec.metallurgical.humanresources.job.view.JobTableView";
	
	protected AbstractTable tableViewer; 
	
	@Override
	public void createContents(Composite parent){
		createTable(parent);
	}
	
	private void createTable(Composite parent){
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));		
		tableViewer = new JobTable(composite);		
		tableViewer.setContentProvider(new GenericContentProvider<Job>());
		tableViewer.setLabelProvider(new JobLabelProvider());
		MenuManager menuManager = new MenuManager();
	    Menu menu = menuManager.createContextMenu(tableViewer.getTable());
	    tableViewer.getTable().setMenu(menu);
	    getSite().registerContextMenu(menuManager,tableViewer.getViewer());
		getSite().setSelectionProvider(tableViewer.getViewer());
		tableViewer.addDoubleClickListener(CommandFactory.getDoubleClickCommand(ICommands.JOB_EDIT,getSite()));
	}
	
	public String getPartName() {
		return Messages.getMessage("job.titles");
	};	

	@Override
	public void setFocus() {
//		tableViewer.getTable().setFocus();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		SearchSelection<Job> selection = (SearchSelection<Job>)event.getSelection();
		tableViewer.setInput(selection.toList());		
	}

	@Override
	protected void addListeners() {
		
	}
	
}
