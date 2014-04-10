package com.rochatec.athena.security.profile.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.table.AbstractTable;

public class ProfileTable extends AbstractTable{

	public ProfileTable(Composite parent) {
		super(parent);
	}	

	@Override
	protected void buildCollumns(TableViewer tableViewer) {
		TableViewerColumn id = new TableViewerColumn(tableViewer, SWT.CENTER);
		id.getColumn().setText(Messages.getMessage("profile.field.label.id"));
		id.getColumn().setWidth(100);
		id.getColumn().setResizable(true);
		id.getColumn().setMoveable(false);
		id.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn label = new TableViewerColumn(tableViewer, SWT.LEFT);
		label.getColumn().setText(Messages.getMessage("profile.field.label.name"));
		label.getColumn().setWidth(450);
		label.getColumn().setResizable(true);
		label.getColumn().setMoveable(false);
		label.getColumn().setAlignment(SWT.LEFT);
		
	}

}
