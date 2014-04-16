package com.rochatec.athena.supply.supplier.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.table.AbstractTable;

public class SupplierTable extends AbstractTable{

	public SupplierTable(Composite parent) {
		super(parent);
	}

	@Override
	protected void buildCollumns(TableViewer tableViewer) {
		TableViewerColumn id = new TableViewerColumn(tableViewer, SWT.CENTER);
		id.getColumn().setText(Messages.getMessage("supplier.field.label.id"));
		id.getColumn().setWidth(70);
		id.getColumn().setResizable(true);
		id.getColumn().setMoveable(false);
		id.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn socialSecurity = new TableViewerColumn(tableViewer,SWT.CENTER);
		socialSecurity.getColumn().setText(Messages.getMessage("supplier.field.label.socialSecurity"));
		socialSecurity.getColumn().setWidth(150);
		socialSecurity.getColumn().setResizable(true);
		socialSecurity.getColumn().setMoveable(true);
		socialSecurity.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn businessName = new TableViewerColumn(tableViewer,SWT.LEFT);
		businessName.getColumn().setText(Messages.getMessage("supplier.field.label.businessName"));
		businessName.getColumn().setWidth(250);
		businessName.getColumn().setResizable(true);
		businessName.getColumn().setMoveable(true);
		businessName.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn tradeName = new TableViewerColumn(tableViewer,SWT.LEFT);
		tradeName.getColumn().setText(Messages.getMessage("supplier.field.label.tradeName"));
		tradeName.getColumn().setWidth(200);
		tradeName.getColumn().setResizable(true);
		tradeName.getColumn().setMoveable(true);
		tradeName.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn registerNumber = new TableViewerColumn(tableViewer,SWT.CENTER);
		registerNumber.getColumn().setText(Messages.getMessage("supplier.field.label.registerNumber"));
		registerNumber.getColumn().setWidth(100);
		registerNumber.getColumn().setResizable(true);
		registerNumber.getColumn().setMoveable(true);
		registerNumber.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn dateRegister = new TableViewerColumn(tableViewer,SWT.CENTER);
		dateRegister.getColumn().setText(Messages.getMessage("supplier.field.label.dateRegister"));
		dateRegister.getColumn().setWidth(100);
		dateRegister.getColumn().setResizable(true);
		dateRegister.getColumn().setMoveable(true);
		dateRegister.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn street = new TableViewerColumn(tableViewer,SWT.LEFT);
		street.getColumn().setText(Messages.getMessage("supplier.field.label.street"));
		street.getColumn().setWidth(200);
		street.getColumn().setResizable(true);
		street.getColumn().setMoveable(true);
		street.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn streetNumber = new TableViewerColumn(tableViewer,SWT.CENTER);
		streetNumber.getColumn().setText(Messages.getMessage("supplier.field.label.streetnumber"));
		streetNumber.getColumn().setWidth(100);
		streetNumber.getColumn().setResizable(true);
		streetNumber.getColumn().setMoveable(true);
		streetNumber.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn zipCode = new TableViewerColumn(tableViewer,SWT.CENTER);
		zipCode.getColumn().setText(Messages.getMessage("supplier.field.label.zipcode"));
		zipCode.getColumn().setWidth(100);
		zipCode.getColumn().setResizable(true);
		zipCode.getColumn().setMoveable(true);
		zipCode.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn neighborhood = new TableViewerColumn(tableViewer,SWT.LEFT);
		neighborhood.getColumn().setText(Messages.getMessage("supplier.field.label.neighborhood"));
		neighborhood.getColumn().setWidth(200);
		neighborhood.getColumn().setResizable(true);
		neighborhood.getColumn().setMoveable(true);
		neighborhood.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn city = new TableViewerColumn(tableViewer,SWT.LEFT);
		city.getColumn().setText(Messages.getMessage("supplier.field.label.city"));
		city.getColumn().setWidth(150);
		city.getColumn().setResizable(true);
		city.getColumn().setMoveable(true);
		city.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn province = new TableViewerColumn(tableViewer,SWT.CENTER);
		province.getColumn().setText(Messages.getMessage("supplier.field.label.province"));
		province.getColumn().setWidth(40);
		province.getColumn().setResizable(true);
		province.getColumn().setMoveable(true);
		province.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn status = new TableViewerColumn(tableViewer,SWT.CENTER);
		status.getColumn().setText(Messages.getMessage("supplier.field.label.status"));
		status.getColumn().setWidth(50);
		status.getColumn().setResizable(true);
		status.getColumn().setMoveable(true);
		status.getColumn().setAlignment(SWT.CENTER);		
	}

}
