package com.rochatec.athena.humanresources.employee.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.table.AbstractTable;

public class EmployeeTable extends AbstractTable{

	public EmployeeTable(Composite parent) {
		super(parent);
	}

	@Override
	protected void buildCollumns(TableViewer tableViewer) {
		TableViewerColumn id = new TableViewerColumn(tableViewer,SWT.CENTER);
		id.getColumn().setText(Messages.getMessage("employee.field.label.id"));
		id.getColumn().setWidth(75);
		id.getColumn().setResizable(true);
		id.getColumn().setMoveable(true);
		id.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn hiredate = new TableViewerColumn(tableViewer,SWT.CENTER);
		hiredate.getColumn().setText(Messages.getMessage("employee.field.label.hiredate"));
		hiredate.getColumn().setWidth(100);
		hiredate.getColumn().setResizable(true);
		hiredate.getColumn().setMoveable(true);
		hiredate.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn socialSecurity = new TableViewerColumn(tableViewer,SWT.CENTER);
		socialSecurity.getColumn().setText(Messages.getMessage("employee.field.label.socialsecurity"));
		socialSecurity.getColumn().setWidth(120);
		socialSecurity.getColumn().setResizable(true);
		socialSecurity.getColumn().setMoveable(true);
		socialSecurity.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn name = new TableViewerColumn(tableViewer,SWT.LEFT);
		name.getColumn().setText(Messages.getMessage("employee.field.label.name"));
		name.getColumn().setWidth(200);
		name.getColumn().setResizable(true);
		name.getColumn().setMoveable(true);
		name.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn job = new TableViewerColumn(tableViewer,SWT.LEFT);
		job.getColumn().setText(Messages.getMessage("employee.field.label.job"));
		job.getColumn().setWidth(130);
		job.getColumn().setResizable(true);
		job.getColumn().setMoveable(true);
		job.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn street = new TableViewerColumn(tableViewer,SWT.LEFT);
		street.getColumn().setText(Messages.getMessage("employee.field.label.street"));
		street.getColumn().setWidth(200);
		street.getColumn().setResizable(true);
		street.getColumn().setMoveable(true);
		street.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn addressNumber = new TableViewerColumn(tableViewer,SWT.CENTER);
		addressNumber.getColumn().setText(Messages.getMessage("employee.field.label.addressnumber"));
		addressNumber.getColumn().setWidth(75);
		addressNumber.getColumn().setResizable(true);
		addressNumber.getColumn().setMoveable(true);
		addressNumber.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn zipCode = new TableViewerColumn(tableViewer,SWT.CENTER);
		zipCode.getColumn().setText(Messages.getMessage("employee.field.label.zipcode"));
		zipCode.getColumn().setWidth(100);
		zipCode.getColumn().setResizable(true);
		zipCode.getColumn().setMoveable(true);
		zipCode.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn neighborhood = new TableViewerColumn(tableViewer,SWT.LEFT);
		neighborhood.getColumn().setText(Messages.getMessage("employee.field.label.neighborhood"));
		neighborhood.getColumn().setWidth(200);
		neighborhood.getColumn().setResizable(true);
		neighborhood.getColumn().setMoveable(true);
		neighborhood.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn city = new TableViewerColumn(tableViewer,SWT.LEFT);
		city.getColumn().setText(Messages.getMessage("employee.field.label.city"));
		city.getColumn().setWidth(150);
		city.getColumn().setResizable(true);
		city.getColumn().setMoveable(true);
		city.getColumn().setAlignment(SWT.LEFT);
		
		TableViewerColumn province = new TableViewerColumn(tableViewer,SWT.CENTER);
		province.getColumn().setText(Messages.getMessage("employee.field.label.province"));
		province.getColumn().setWidth(40);
		province.getColumn().setResizable(true);
		province.getColumn().setMoveable(true);
		province.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn status = new TableViewerColumn(tableViewer,SWT.CENTER);
		status.getColumn().setText(Messages.getMessage("employee.field.label.status"));
		status.getColumn().setWidth(50);
		status.getColumn().setResizable(true);
		status.getColumn().setMoveable(true);
		status.getColumn().setAlignment(SWT.CENTER);
		
	}

}
