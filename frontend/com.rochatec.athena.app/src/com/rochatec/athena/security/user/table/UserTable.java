package com.rochatec.athena.security.user.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.table.AbstractTable;

public class UserTable extends AbstractTable{

	public UserTable(Composite parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void buildCollumns(TableViewer tableViewer) {
		TableViewerColumn id = new TableViewerColumn(tableViewer, SWT.CENTER);
		id.getColumn().setText(Messages.getMessage("user.field.label.id"));
		id.getColumn().setWidth(70);
		id.getColumn().setResizable(true);
		id.getColumn().setMoveable(false);
		id.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn profile = new TableViewerColumn(tableViewer, SWT.LEFT);
		profile.getColumn().setText(Messages.getMessage("user.field.label.profile"));
		profile.getColumn().setWidth(230);
		profile.getColumn().setResizable(true);
		profile.getColumn().setMoveable(false);
		profile.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn username = new TableViewerColumn(tableViewer, SWT.CENTER);
		username.getColumn().setText(Messages.getMessage("user.field.label.username"));
		username.getColumn().setWidth(200);
		username.getColumn().setResizable(true);
		username.getColumn().setMoveable(false);
		username.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn employee = new TableViewerColumn(tableViewer,SWT.LEFT);
		employee.getColumn().setText(Messages.getMessage("user.field.label.name"));
		employee.getColumn().setWidth(300);
		employee.getColumn().setResizable(true);
		employee.getColumn().setMoveable(true);
		employee.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn status = new TableViewerColumn(tableViewer, SWT.CENTER);
		status.getColumn().setText(Messages.getMessage("user.field.label.active"));
		status.getColumn().setWidth(100);
		status.getColumn().setResizable(true);
		status.getColumn().setMoveable(false);
		status.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn blocked = new TableViewerColumn(tableViewer,SWT.CENTER);
		blocked.getColumn().setText(Messages.getMessage("user.field.label.blocked"));
		blocked.getColumn().setWidth(100);
		blocked.getColumn().setResizable(true);
		blocked.getColumn().setMoveable(true);
		blocked.getColumn().setAlignment(SWT.CENTER);
		
	}

}
