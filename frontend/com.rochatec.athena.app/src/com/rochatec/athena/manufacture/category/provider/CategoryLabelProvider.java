package com.rochatec.athena.manufacture.category.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.Category;
import com.rochatec.athena.util.IPathIcons;

public class CategoryLabelProvider extends LabelProvider implements ITableLabelProvider{

	
	public Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex == 1){
			return Activator.getImageDescriptor(IPathIcons.CATEGORY_16).createImage();
		}
		return null;
	}
	
	public String getText(Object element) {
		Category category = (Category)element;
		return category.getName();
	}

	
	public String getColumnText(Object element, int columnIndex) {
		Category category = (Category) element;
		switch (columnIndex) {
		case 0:
			return Long.toString(category.getId());
		case 1:
			return category.getName();	
		}
		return null;
	}

}
