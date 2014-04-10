package com.rochatec.athena.humanresources.job.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.table.AbstractTable;

public class JobTable extends AbstractTable{

	public JobTable(Composite parent) {
		super(parent);		
	}

	@Override
	protected void buildCollumns(TableViewer tableViewer) {
		TableViewerColumn id = new TableViewerColumn(tableViewer, SWT.CENTER);
		id.getColumn().setText(Messages.getMessage("job.field.id.label"));
		id.getColumn().setWidth(100);
		id.getColumn().setResizable(true);
		id.getColumn().setMoveable(false);
		id.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn name = new TableViewerColumn(tableViewer, SWT.LEFT);
		name.getColumn().setText(Messages.getMessage("job.field.name.label"));
		name.getColumn().setWidth(300);
		name.getColumn().setResizable(true);
		name.getColumn().setMoveable(false);
		name.getColumn().setAlignment(SWT.LEFT);		
	}

}
