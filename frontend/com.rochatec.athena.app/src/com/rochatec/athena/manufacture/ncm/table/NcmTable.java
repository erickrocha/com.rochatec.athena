package com.rochatec.athena.manufacture.ncm.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.table.AbstractTable;

public class NcmTable extends AbstractTable{

	public NcmTable(Composite parent) {
		super(parent);		
	}

	@Override
	protected void buildCollumns(TableViewer tableViewer) {
		TableViewerColumn code = new TableViewerColumn(tableViewer,SWT.CENTER);
		code.getColumn().setText(Messages.getMessage("ncm.field.label.code"));
		code.getColumn().setWidth(100);
		code.getColumn().setResizable(true);
		code.getColumn().setMoveable(true);
		code.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn description = new TableViewerColumn(tableViewer,SWT.LEFT);
		description.getColumn().setText(Messages.getMessage("ncm.field.label.description"));
		description.getColumn().setWidth(400);
		description.getColumn().setResizable(true);
		description.getColumn().setMoveable(true);
		description.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn unitMeasure = new TableViewerColumn(tableViewer,SWT.LEFT);
		unitMeasure.getColumn().setText(Messages.getMessage("ncm.field.label.unitmeasure"));
		unitMeasure.getColumn().setWidth(100);
		unitMeasure.getColumn().setResizable(true);
		unitMeasure.getColumn().setMoveable(true);
		unitMeasure.getColumn().setAlignment(SWT.LEFT);
	}

}
