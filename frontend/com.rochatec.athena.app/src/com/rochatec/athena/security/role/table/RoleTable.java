package com.rochatec.athena.security.role.table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Role;
import com.rochatec.framework.bind.Editable;
import com.rochatec.graphics.table.AbstractCheckTable;

public class RoleTable extends AbstractCheckTable{
	
	private Editable editable;
	
	public RoleTable(Composite parent,Editable editable) {
		super(parent);
		this.editable = editable;
	}

	public Role getSelected(){
		Role object = (Role)tableViewer.getElementAt(tableViewer.getTable().getSelectionIndex());
		return object;
	}
	
	public String getRowsCount(){
		return Messages.getMessage("app.rowsfound")+" "+tableViewer.getTable().getItemCount();
	}
	
	public Set<Role> getCheckeds(){
		Set<Role> roles = new HashSet<Role>();
		for (Object checked : tableViewer.getCheckedElements()){
			roles.add((Role)checked);
		}
		return roles;
	}
	
	public void setCheckeds(Set<Role> roles){
		tableViewer.setCheckedElements(roles.toArray());
	}
	
	public void setCheckeds(List<Role> roles){
		setCheckeds(new HashSet<Role>(roles));
	}
	
	public void setCheckedAll(boolean checked){
		tableViewer.setAllChecked(checked);
	}
	
	public boolean isCheckedAll(){
		return tableViewer.getCheckedElements().length == tableViewer.getTable().getItemCount();
	}

	@Override
	protected void buildCollumns(TableViewer tableViewer) {
		TableViewerColumn key = new TableViewerColumn(tableViewer, SWT.CENTER);
		key.getColumn().setText(Messages.getMessage("role.field.label.key"));
		key.getColumn().setWidth(130);
		key.getColumn().setResizable(true);
		key.getColumn().setMoveable(false);
		key.getColumn().setAlignment(SWT.CENTER);
				
		TableViewerColumn label = new TableViewerColumn(tableViewer, SWT.LEFT);
		label.getColumn().setText(Messages.getMessage("role.field.label.label"));
		label.getColumn().setWidth(450);
		label.getColumn().setResizable(true);
		label.getColumn().setMoveable(false);
		label.getColumn().setAlignment(SWT.LEFT);
		
	}
	
	@Override
	protected void addListeners() {
		tableViewer.addCheckStateListener(new RoleCheckStateListener());		
	}
	
	class RoleCheckStateListener implements ICheckStateListener{

		@Override
		public void checkStateChanged(CheckStateChangedEvent event) {
			editable.setDirty(true);			
		}		
	}

}
