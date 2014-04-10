package com.rochatec.athena.address.province.provider;

import org.eclipse.jface.viewers.LabelProvider;

import com.rochatec.athena.model.Province;

public class ProvinceLabelProvider extends LabelProvider{

	@Override
	public String getText(Object element) {
		Province province = (Province)element;
		return province.getAcronym();
	}
}
