package com.rochatec.athena.security.profile.view;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import com.rochatec.athena.model.Profile;
import com.rochatec.athena.security.profile.provider.ProfileLabelProvider;
import com.rochatec.athena.security.profile.table.ProfileTable;
import com.rochatec.athena.util.CommandFactory;
import com.rochatec.athena.util.ICommands;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.table.AbstractTable;
import com.rochatec.graphics.view.AbstractView;

public class ProfileTableView extends AbstractView implements ISelectionChangedListener{
	
	public static final String ID = "com.rochatec.athena.security.profile.view.ProfileTableView";
	protected AbstractTable tableViewer;
	
	@Override
	protected void createContents(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		tableViewer = new ProfileTable(composite);		
		tableViewer.setContentProvider(new GenericContentProvider<Profile>());
		tableViewer.setLabelProvider(new ProfileLabelProvider());
		MenuManager menuManager = new MenuManager();
	    Menu menu = menuManager.createContextMenu(tableViewer.getTable());
	    tableViewer.getTable().setMenu(menu);
	    getSite().registerContextMenu(menuManager, tableViewer.getViewer());
		getSite().setSelectionProvider(tableViewer.getViewer());
		tableViewer.addDoubleClickListener(CommandFactory.getDoubleClickCommand(ICommands.PROFILE_EDIT,getSite()));
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
		SearchSelection<Profile> selection = (SearchSelection<Profile>)event.getSelection();
		tableViewer.setInput(selection.toList());
		
	}

}
