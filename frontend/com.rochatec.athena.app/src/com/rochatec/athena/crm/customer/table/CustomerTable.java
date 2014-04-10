package com.rochatec.athena.crm.customer.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.table.AbstractTable;

public class CustomerTable extends AbstractTable{

	public CustomerTable(Composite parent) {
		super(parent);
	}

	@Override
	protected void buildCollumns(TableViewer tableViewer) {
		TableViewerColumn id = new TableViewerColumn(tableViewer, SWT.CENTER);
		id.getColumn().setText(Messages.getMessage("customer.field.label.id"));
		id.getColumn().setWidth(70);
		id.getColumn().setResizable(true);
		id.getColumn().setMoveable(false);
		id.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn socialSecurity = new TableViewerColumn(tableViewer,SWT.CENTER);
		socialSecurity.getColumn().setText(Messages.getMessage("customer.field.label.socialSecurity"));
		socialSecurity.getColumn().setWidth(120);
		socialSecurity.getColumn().setResizable(true);
		socialSecurity.getColumn().setMoveable(true);
		socialSecurity.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn name = new TableViewerColumn(tableViewer,SWT.LEFT);
		name.getColumn().setText(Messages.getMessage("customer.field.label.name"));
		name.getColumn().setWidth(200);
		name.getColumn().setResizable(true);
		name.getColumn().setMoveable(true);
		name.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn registerNumber = new TableViewerColumn(tableViewer,SWT.CENTER);
		registerNumber.getColumn().setText(Messages.getMessage("customer.field.label.registerNumber"));
		registerNumber.getColumn().setWidth(100);
		registerNumber.getColumn().setResizable(true);
		registerNumber.getColumn().setMoveable(true);
		registerNumber.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn street = new TableViewerColumn(tableViewer,SWT.LEFT);
		street.getColumn().setText(Messages.getMessage("customer.field.label.street"));
		street.getColumn().setWidth(200);
		street.getColumn().setResizable(true);
		street.getColumn().setMoveable(true);
		street.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn streetNumber = new TableViewerColumn(tableViewer,SWT.CENTER);
		streetNumber.getColumn().setText(Messages.getMessage("customer.field.label.streetNumber"));
		streetNumber.getColumn().setWidth(100);
		streetNumber.getColumn().setResizable(true);
		streetNumber.getColumn().setMoveable(true);
		streetNumber.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn zipCode = new TableViewerColumn(tableViewer,SWT.CENTER);
		zipCode.getColumn().setText(Messages.getMessage("customer.field.label.zipcode"));
		zipCode.getColumn().setWidth(100);
		zipCode.getColumn().setResizable(true);
		zipCode.getColumn().setMoveable(true);
		zipCode.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn neighborhood = new TableViewerColumn(tableViewer,SWT.LEFT);
		neighborhood.getColumn().setText(Messages.getMessage("customer.field.label.neighborhood"));
		neighborhood.getColumn().setWidth(200);
		neighborhood.getColumn().setResizable(true);
		neighborhood.getColumn().setMoveable(true);
		neighborhood.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn city = new TableViewerColumn(tableViewer,SWT.LEFT);
		city.getColumn().setText(Messages.getMessage("customer.field.label.city"));
		city.getColumn().setWidth(150);
		city.getColumn().setResizable(true);
		city.getColumn().setMoveable(true);
		city.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn province = new TableViewerColumn(tableViewer,SWT.CENTER);
		province.getColumn().setText(Messages.getMessage("customer.field.label.province"));
		province.getColumn().setWidth(40);
		province.getColumn().setResizable(true);
		province.getColumn().setMoveable(true);
		province.getColumn().setAlignment(SWT.CENTER);
		
	}

}
