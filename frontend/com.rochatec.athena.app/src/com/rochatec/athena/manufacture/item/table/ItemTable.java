package com.rochatec.athena.manufacture.item.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.table.AbstractTable;

public class ItemTable extends AbstractTable{

	public ItemTable(Composite parent) {
		super(parent);		
	}

	@Override
	protected void buildCollumns(TableViewer tableViewer) {
		TableViewerColumn id = new TableViewerColumn(tableViewer, SWT.CENTER);
		id.getColumn().setText(Messages.getMessage("productItem.field.label.id"));
		id.getColumn().setWidth(80);
		id.getColumn().setResizable(true);
		id.getColumn().setMoveable(false);
		id.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn name = new TableViewerColumn(tableViewer, SWT.LEFT);
		name.getColumn().setText(Messages.getMessage("productItem.field.label.name"));
		name.getColumn().setWidth(450);
		name.getColumn().setResizable(true);
		name.getColumn().setMoveable(false);
		name.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn quantity = new TableViewerColumn(tableViewer, SWT.LEFT);
		quantity.getColumn().setText(Messages.getMessage("productItem.field.label.quantity"));
		quantity.getColumn().setWidth(150);
		quantity.getColumn().setResizable(true);
		quantity.getColumn().setMoveable(false);
		quantity.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn sellPrice = new TableViewerColumn(tableViewer, SWT.LEFT);
		sellPrice.getColumn().setText(Messages.getMessage("productItem.field.label.sellPrice"));
		sellPrice.getColumn().setWidth(150);
		sellPrice.getColumn().setResizable(true);
		sellPrice.getColumn().setMoveable(false);
		sellPrice.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn icms = new TableViewerColumn(tableViewer, SWT.LEFT);
		icms.getColumn().setText(Messages.getMessage("productItem.field.label.icms"));
		icms.getColumn().setWidth(150);
		icms.getColumn().setResizable(true);
		icms.getColumn().setMoveable(false);
		icms.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn ipi = new TableViewerColumn(tableViewer, SWT.LEFT);
		ipi.getColumn().setText(Messages.getMessage("productItem.field.label.ipi"));
		ipi.getColumn().setWidth(150);
		ipi.getColumn().setResizable(true);
		ipi.getColumn().setMoveable(false);
		ipi.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn totalItem = new TableViewerColumn(tableViewer, SWT.LEFT);
		totalItem.getColumn().setText(Messages.getMessage("productItem.field.label.totalItem"));
		totalItem.getColumn().setWidth(150);
		totalItem.getColumn().setResizable(true);
		totalItem.getColumn().setMoveable(false);
		totalItem.getColumn().setAlignment(SWT.LEFT);
	}

}
