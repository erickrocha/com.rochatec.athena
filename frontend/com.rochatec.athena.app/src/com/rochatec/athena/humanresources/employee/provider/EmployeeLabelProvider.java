package com.rochatec.athena.humanresources.employee.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.util.StatusTradutor;
import com.rochatec.framework.exception.BadFormatException;

public class EmployeeLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int index) {
		Employee employee = (Employee) element;
		try {
			switch (index) {
			case 0:
				return employee.getId().toString();
			case 1:
				return Formatter.getDate().mask(employee.getHiredate());
			case 2:
				return Formatter.getSocialSecurity().mask(
						employee.getSocialSecurity());
			case 3:
				return employee.getName();
			case 4:
				return employee.getJob().getName();
			case 5:
				return employee.getAddress().getStreet();
			case 6:
				return employee.getAddressNumber();
			case 7:
				return Formatter.getZipCode().mask(
						employee.getAddress().getZipcode());
			case 8:
				return employee.getAddress().getNeighborhood();
			case 9:
				return employee.getAddress().getCity();
			case 10:
				return employee.getAddress().getProvince().getAcronym();
			case 11:
				return StatusTradutor.getLabel(employee.getStatus());
			}			
		} catch (BadFormatException e) {
			Activator.getDefault().addConsoleError(e);
		}
		return null;
	}

}
