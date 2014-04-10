package com.rochatec.athena.security.profile.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.Profile;
import com.rochatec.athena.util.IPathIcons;

public class ProfileLabelProvider extends LabelProvider implements ITableLabelProvider{

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex == 1){
			Activator.getImageDescriptor(IPathIcons.PROFILE_16).createImage();
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Profile profile = (Profile)element;
		switch (columnIndex) {
		case 0:
			return profile.getId().toString();
		case 1:
			return profile.getLabel();
		default:
			break;
		}
		return null;
	}

}
