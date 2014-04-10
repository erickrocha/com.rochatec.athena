package com.rochatec.athena.supply.shipper.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.Shipper;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.util.StatusTradutor;
import com.rochatec.framework.exception.BadFormatException;

public class ShipperLabelProvider extends LabelProvider implements ITableLabelProvider{

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
	
	@Override
	public String getText(Object element) {
		return ((Shipper)element).getBusinessName();
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		try {
			Shipper shipper = (Shipper)element;
		
			switch (columnIndex) {
			case 0:
				return shipper.getId().toString();
			case 1:
				return Formatter.getSocialSecurity().mask(shipper.getSocialSecurity());
			case 2:
				return shipper.getBusinessName();
			case 3:
				return shipper.getComercialName();
			case 4:
				return shipper.getRegisterNumber();
			case 5:
				return shipper.getCityRegister();
			case 6:
				return shipper.getAddress().getStreet();
			case 7:
				return shipper.getAddressNumber();
			case 8:
				return shipper.getZipcode();
			case 9:
				return shipper.getAddress().getNeighborhood();
			case 10:
				return shipper.getAddress().getCity();
			case 11:
				return shipper.getAddress().getProvince().getAcronym();
			case 12:
				return StatusTradutor.getLabel(shipper.getStatus());
			default:
				break;
			}
		}catch (BadFormatException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}

}
