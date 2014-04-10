package com.rochatec.athena.crm.customer.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.util.Formatter;
import com.rochatec.framework.exception.BadFormatException;

public class CustomerLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		try {
			Customer customer = (Customer) element;
			switch (columnIndex) {
			case 0:
				return customer.getId().toString();
			case 1:
				return Formatter.getSocialSecurity().mask(customer.getSocialSecurity());
			case 2:
				return customer.getName();
			case 3:
				return customer.getRegisterNumber();
			case 4:
				return customer.getAddress().getStreet();
			case 5:
				return customer.getAddressNumber();
			case 6:
				return Formatter.getZipCode().mask(customer.getZipcode());
			case 7:
				return customer.getAddress().getNeighborhood();
			case 8:
				return customer.getAddress().getCity();
			case 9:
				return customer.getAddress().getProvince().getAcronym();
			}			
		} catch (BadFormatException ex) {
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}

}
