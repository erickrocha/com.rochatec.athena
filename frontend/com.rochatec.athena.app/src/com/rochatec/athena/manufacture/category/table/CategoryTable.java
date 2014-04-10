package com.rochatec.athena.manufacture.category.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.table.AbstractTable;

public class CategoryTable extends AbstractTable{

	public CategoryTable(Composite parent) {
		super(parent);		
	}

	@Override
	protected void buildCollumns(TableViewer tableViewer) {
		TableViewerColumn id = new TableViewerColumn(tableViewer,SWT.CENTER);
		id.getColumn().setText(Messages.getMessage("category.field.label.id"));
		id.getColumn().setWidth(150);
		id.getColumn().setResizable(true);
		id.getColumn().setMoveable(true);
		id.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn name = new TableViewerColumn(tableViewer,SWT.LEFT);
		name.getColumn().setText(Messages.getMessage("category.field.label.name"));
		name.getColumn().setWidth(350);
		name.getColumn().setResizable(true);
		name.getColumn().setMoveable(true);
		name.getColumn().setAlignment(SWT.LEFT);
		
	}

}
