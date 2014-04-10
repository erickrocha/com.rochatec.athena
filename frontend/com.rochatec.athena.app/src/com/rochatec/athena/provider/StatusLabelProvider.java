package com.rochatec.athena.provider;

import org.eclipse.jface.viewers.LabelProvider;

import com.rochatec.athena.model.Status;
import com.rochatec.athena.util.StatusTradutor;

public class StatusLabelProvider extends LabelProvider{

	@Override
	public String getText(Object element) {
		return StatusTradutor.getLabel((Status)element);
	}
}
