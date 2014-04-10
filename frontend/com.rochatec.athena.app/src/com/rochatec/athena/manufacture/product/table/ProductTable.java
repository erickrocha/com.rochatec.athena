package com.rochatec.athena.manufacture.product.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.table.AbstractTable;

public class ProductTable extends AbstractTable{

	public ProductTable(Composite parent) {
		super(parent);		
	}

	@Override
	protected void buildCollumns(TableViewer tableViewer) {
		TableViewerColumn id = new TableViewerColumn(tableViewer, SWT.CENTER);
		id.getColumn().setText(Messages.getMessage("product.field.label.id"));
		id.getColumn().setWidth(70);
		id.getColumn().setResizable(true);
		id.getColumn().setMoveable(false);
		id.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn category = new TableViewerColumn(tableViewer, SWT.LEFT);
		category.getColumn().setText(Messages.getMessage("product.field.label.category"));
		category.getColumn().setWidth(150);
		category.getColumn().setResizable(true);
		category.getColumn().setMoveable(false);
		category.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn name = new TableViewerColumn(tableViewer, SWT.LEFT);
		name.getColumn().setText(Messages.getMessage("product.field.label.name"));
		name.getColumn().setWidth(350);
		name.getColumn().setResizable(true);
		name.getColumn().setMoveable(false);
		name.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn unitMeasure = new TableViewerColumn(tableViewer,SWT.CENTER);
		unitMeasure.getColumn().setText(Messages.getMessage("product.field.label.unitmeasure"));
		unitMeasure.getColumn().setWidth(75);
		unitMeasure.getColumn().setResizable(true);
		unitMeasure.getColumn().setMoveable(true);
		unitMeasure.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn stock = new TableViewerColumn(tableViewer,SWT.CENTER);
		stock.getColumn().setText(Messages.getMessage("product.field.label.stock"));
		stock.getColumn().setWidth(100);
		stock.getColumn().setResizable(true);
		stock.getColumn().setMoveable(true);
		stock.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn costPrice = new TableViewerColumn(tableViewer,SWT.CENTER);
		costPrice.getColumn().setText(Messages.getMessage("product.field.label.costPrice"));
		costPrice.getColumn().setWidth(100);
		costPrice.getColumn().setResizable(true);
		costPrice.getColumn().setMoveable(true);
		costPrice.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn sellPrice = new TableViewerColumn(tableViewer,SWT.CENTER);
		sellPrice.getColumn().setText(Messages.getMessage("product.field.label.sellPrice"));
		sellPrice.getColumn().setWidth(100);
		sellPrice.getColumn().setResizable(true);
		sellPrice.getColumn().setMoveable(true);
		sellPrice.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn icms = new TableViewerColumn(tableViewer,SWT.CENTER);
		icms.getColumn().setText(Messages.getMessage("product.field.label.icms"));
		icms.getColumn().setWidth(150);
		icms.getColumn().setResizable(true);
		icms.getColumn().setMoveable(true);
		icms.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn ipi = new TableViewerColumn(tableViewer,SWT.CENTER);
		ipi.getColumn().setText(Messages.getMessage("product.field.label.ipi"));
		ipi.getColumn().setWidth(75);
		ipi.getColumn().setResizable(true);
		ipi.getColumn().setMoveable(true);
		ipi.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn active = new TableViewerColumn(tableViewer,SWT.CENTER);
		active.getColumn().setText(Messages.getMessage("product.field.label.status"));
		active.getColumn().setWidth(100);
		active.getColumn().setResizable(true);
		active.getColumn().setMoveable(true);
		active.getColumn().setAlignment(SWT.CENTER);
		
	}

}
