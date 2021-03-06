package com.rochatec.athena.manufacture.productSet.provider;

import java.math.BigDecimal;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.ProductSet;
import com.rochatec.athena.util.Formatter;
import com.rochatec.athena.util.StatusTradutor;
import com.rochatec.athena.util.UnitMeasureTradutor;
import com.rochatec.framework.exception.BadFormatException;

public class ProductSetLabelProvider extends LabelProvider implements ITableLabelProvider{
	
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		try{
			ProductSet product = (ProductSet)element;
		
			switch (columnIndex) {
			case 0:
				return Long.toString(product.getId()); 
			case 1:
				return product.getCategory().getName();
			case 2:
				return product.getName();
			case 3:
				return UnitMeasureTradutor.getLabel(product.getUnitMeasure());
			case 4:
				return Formatter.getWeight().mask(product.getStock() != null ? product.getStock() : BigDecimal.ZERO);
			case 5:
				return Formatter.getCurrency().mask(product.getCostprice() != null ? product.getCostprice() : BigDecimal.ZERO);
			case 6:
				return Formatter.getCurrency().mask(product.getSellprice() != null ? product.getSellprice() : BigDecimal.ZERO);
			case 7:
				return product.getIcms().getDescription();
			case 8:
				return Formatter.getCurrency().mask(product.getIpi() != null ? product.getIpi() : BigDecimal.ZERO);
			case 9:
				return StatusTradutor.getLabel(product.getStatus());
			}
		}catch (BadFormatException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}

}
