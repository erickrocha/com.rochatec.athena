package com.rochatec.athena.invoice.item.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.model.InvoiceInputItem;
import com.rochatec.athena.util.Formatter;
import com.rochatec.framework.exception.BadFormatException;

public class InvoiceItemLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {

		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		try {
			InvoiceInputItem item = (InvoiceInputItem) element;
			switch (columnIndex) {
			case 0:
				return item.getProductId().toString();
			case 1:
				return item.getLabel();
			case 2:
				return Formatter.getWeight().mask(item.getQuantity());
			case 3:
				return Formatter.getDecimal().mask(item.getCostPrice());
			case 4:
				return Formatter.getDecimal().mask(item.getTotalItems());
			case 5:
				return item.getIcms().getDescription();
			case 6:
				return Formatter.getDecimal().mask(item.getTotalIcms());
			case 7:
				return Formatter.getDecimal().mask(item.getIpiBase());
			case 8:
				return Formatter.getDecimal().mask(item.getIpiValue());
			default:
				break;
			}
		} catch (BadFormatException ex) {

		}
		return null;
	}
}
