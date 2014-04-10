package com.rochatec.athena.supply.supplier.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.util.StatusTradutor;
import com.rochatec.framework.exception.BadFormatException;

public class SupplierLabelProvider extends LabelProvider implements ITableLabelProvider{

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
	
	@Override
	public String getText(Object element) {
		return ((Supplier)element).getTradeName();
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		try{
			Supplier supplier = (Supplier)element;
			switch (columnIndex) {
			case 0:
				return supplier.getId().toString();
			case 1:
				return Formatter.getSocialSecurity().mask(supplier.getSocialSecurity());
			case 2:
				return supplier.getCompanyName();
			case 3:
				return supplier.getTradeName();
			case 4:
				return supplier.getRegisterNumber();
			case 5:
				return Formatter.getDate().mask(supplier.getDateRegister());
			case 6:
				return supplier.getAddress().getStreet();
			case 7:
				return supplier.getAddressNumber();
			case 8:
				return Formatter.getZipCode().mask(supplier.getZipcode());
			case 9:
				return supplier.getAddress().getNeighborhood();
			case 10:
				return supplier.getAddress().getCity();
			case 11:
				return supplier.getAddress().getProvince().getAcronym();
			case 12:
				return StatusTradutor.getLabel(supplier.getStatus());
			default:
				break;
			}
		}catch(BadFormatException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}

}
