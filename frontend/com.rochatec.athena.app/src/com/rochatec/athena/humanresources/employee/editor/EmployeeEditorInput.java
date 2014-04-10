package com.rochatec.athena.humanresources.employee.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.util.IPathIcons;

public class EmployeeEditorInput implements IEditorInput{
	
	private Employee employee;
	
	public EmployeeEditorInput(Employee employee) {
		this.employee = employee;
	}
	
	public EmployeeEditorInput() {
		this(new Employee());
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		
		return null;
	}

	@Override
	public boolean exists() {
		
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return Activator.getImageDescriptor(IPathIcons.EMPLOYEE_16);
	}

	@Override
	public String getName() {
		return Messages.getMessage("employee.name");
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return getName();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

}
