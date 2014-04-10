package com.rochatec.athena.humanresources.employee.dialog;

import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.client.service.HumanResourceClientService;
import com.rochatec.athena.humanresources.employee.box.EmployeeSearchBox;
import com.rochatec.athena.humanresources.employee.provider.EmployeeLabelProvider;
import com.rochatec.athena.humanresources.employee.table.EmployeeTable;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.dialog.AbstractDialog;
import com.rochatec.graphics.provider.GenericContentProvider;

public class EmployeeDialog extends AbstractDialog<Employee> implements Executable<Employee>{
		
	protected HumanResourceClientService humanResourceClientService = ServiceFactory.getInstance().getHumanResourceClientService();
	
	public EmployeeDialog(Shell owner) {
		super(owner);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(1024,768);
	}

	@Override
	public void createSearchArea(Composite parent) {
		new EmployeeSearchBox(parent,this);
	}

	@Override
	public void createTable(Composite parent) {
		table = new EmployeeTable(parent);
		table.setContentProvider(new GenericContentProvider<Employee>());
		table.setLabelProvider(new EmployeeLabelProvider());		
	}

	@Override
	public String getTitle() {
		return Messages.getMessage("employee.title");
	}
	
	public void execute(List<Employee> employees) {
		table.setInput(employees);
	}

	@Override
	public void execute(Employee object) {
		
	}

}
