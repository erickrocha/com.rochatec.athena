package com.rochatec.athena.invoice.item.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.table.AbstractTable;

public class InvoiceItemTable extends AbstractTable{

	public InvoiceItemTable(Composite parent) {
		super(parent);
	}

	@Override
	protected void buildCollumns(TableViewer tableViewer) {
		TableViewerColumn id = new TableViewerColumn(tableViewer, SWT.CENTER);
		id.getColumn().setText(Messages.getMessage("invoiceItem.product.id.field.label"));
		id.getColumn().setWidth(70);
		id.getColumn().setResizable(true);
		id.getColumn().setMoveable(false);
		id.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn name = new TableViewerColumn(tableViewer, SWT.LEFT);
		name.getColumn().setText(Messages.getMessage("invoiceItem.product.name.field.label"));
		name.getColumn().setWidth(300);
		name.getColumn().setResizable(true);
		name.getColumn().setMoveable(false);
		name.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn quantity = new TableViewerColumn(tableViewer, SWT.CENTER);
		quantity.getColumn().setText(Messages.getMessage("invoiceItem.quantity.field.label"));
		quantity.getColumn().setWidth(120);
		quantity.getColumn().setResizable(true);
		quantity.getColumn().setMoveable(false);
		quantity.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn sellPrice = new TableViewerColumn(tableViewer, SWT.CENTER);
		sellPrice.getColumn().setText(Messages.getMessage("invoiceItem.costPrice.field.label"));
		sellPrice.getColumn().setWidth(120);
		sellPrice.getColumn().setResizable(true);
		sellPrice.getColumn().setMoveable(false);
		sellPrice.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn totalItem = new TableViewerColumn(tableViewer, SWT.CENTER);
		totalItem.getColumn().setText(Messages.getMessage("invoiceItem.totalItems.field.label"));
		totalItem.getColumn().setWidth(120);
		totalItem.getColumn().setResizable(true);
		totalItem.getColumn().setMoveable(false);
		totalItem.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn icms = new TableViewerColumn(tableViewer, SWT.CENTER);
		icms.getColumn().setText(Messages.getMessage("invoiceItem.icms.field.label"));
		icms.getColumn().setWidth(120);
		icms.getColumn().setResizable(true);
		icms.getColumn().setMoveable(false);
		icms.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn valorIcms = new TableViewerColumn(tableViewer, SWT.CENTER);
		valorIcms.getColumn().setText(Messages.getMessage("invoiceItem.totalIcms.field.label"));
		valorIcms.getColumn().setWidth(120);
		valorIcms.getColumn().setResizable(true);
		valorIcms.getColumn().setMoveable(false);
		valorIcms.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn ipi = new TableViewerColumn(tableViewer, SWT.CENTER);
		ipi.getColumn().setText(Messages.getMessage("invoiceItem.ipi.field.label"));
		ipi.getColumn().setWidth(100);
		ipi.getColumn().setResizable(true);
		ipi.getColumn().setMoveable(false);
		ipi.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn valorIpi = new TableViewerColumn(tableViewer, SWT.CENTER);
		valorIpi.getColumn().setText(Messages.getMessage("invoiceItem.totalIpi.field.label"));
		valorIpi.getColumn().setWidth(100);
		valorIpi.getColumn().setResizable(true);
		valorIpi.getColumn().setMoveable(false);
		valorIpi.getColumn().setAlignment(SWT.CENTER);
	}

}
