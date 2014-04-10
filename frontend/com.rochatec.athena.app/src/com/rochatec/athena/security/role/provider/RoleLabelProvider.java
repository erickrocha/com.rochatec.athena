package com.rochatec.athena.security.role.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.Role;
import com.rochatec.athena.util.IPathIcons;

public class RoleLabelProvider extends LabelProvider implements ITableLabelProvider{

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex == 0){
			return Activator.getImageDescriptor(IPathIcons.ROLE_16).createImage();
		}
		return null;
	}
	
	@Override
	public String getText(Object element) {
		return ((Role)element).getDescription();
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Role role = (Role) element;
		switch (columnIndex) {
		case 0:
			return role.getKey();
		case 1:
			return role.getDescription();
		default:
			break;
		}
		return null;
	}

}
