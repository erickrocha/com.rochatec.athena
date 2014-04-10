package com.rochatec.athena.manufacture.unitMeasure.provider;

import org.eclipse.jface.viewers.LabelProvider;

import com.rochatec.athena.model.UnitMeasure;
import com.rochatec.athena.util.UnitMeasureTradutor;

public class UnitMeasureLabelProvider extends LabelProvider{
	
	@Override
	public String getText(Object element) {
		return UnitMeasureTradutor.getLabel((UnitMeasure)element);
	}
}
