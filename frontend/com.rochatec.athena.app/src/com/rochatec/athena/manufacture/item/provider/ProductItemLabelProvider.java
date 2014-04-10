package com.rochatec.athena.manufacture.item.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.IProductItem;
import com.rochatec.athena.util.Formatter;
import com.rochatec.framework.exception.BadFormatException;

public class ProductItemLabelProvider extends LabelProvider implements ITableLabelProvider{

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		IProductItem item = (IProductItem)element;
		try {
			switch (columnIndex) {
			case 0:
				return item.getId().toString();
			case 1:
				return item.getLabel();
			case 2:
				return Formatter.getCurrency().mask(item.getSellPrice());				
			case 3:
				return Formatter.getPercentage().mask(item.getIcms().getPercentage());
			case 4:
				return Formatter.getPercentage().mask(item.getIpi());
			case 5:				
				return Formatter.getWeight().mask(item.getQuantity());
			case 6:
				return Formatter.getCurrency().mask(item.getTotalItem());
			default:
				break;
			}
		} catch (BadFormatException e) {
			Activator.getDefault().addConsoleError(e);
		}
		return null;
	}

}
