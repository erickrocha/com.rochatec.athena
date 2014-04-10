package com.rochatec.athena.humanresources.job.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.model.Job;

public class JobLabelProvider extends LabelProvider implements ITableLabelProvider{
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex == 0)
			return null;
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Job job = (Job)element;
		switch (columnIndex) {
		case 0:
			return job.getId().toString();
		case 1:
			return job.getName();
		default:
			break;
		}
		return null;
	}
}
