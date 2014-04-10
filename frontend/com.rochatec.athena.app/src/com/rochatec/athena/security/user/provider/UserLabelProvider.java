package com.rochatec.athena.security.user.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.User;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.athena.util.StatusTradutor;

public class UserLabelProvider extends LabelProvider implements ITableLabelProvider{

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex == 2){
			Activator.getImageDescriptor(IPathIcons.USER_16).createImage();
		}
		return null;
	}
	
	@Override
	public Image getImage(Object element) {
		return Activator.getImageDescriptor(IPathIcons.USER_16).createImage();
	}
	
	@Override
	public String getText(Object element) {
		User user = (User)element;
		return user.getEmployee() != null ? user.getEmployee().getName() : user.getUsername();
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		User user = (User)element;
		switch (columnIndex) {
		case 0:
			return user.getId().toString();
		case 1:
			return user.getProfile().getLabel();
		case 2:
			return user.getUsername();
		case 3:
			if (user.getUsername().equals("ADMIN")){
				return "Administrador do Sistema";
			}else{
				return user.getEmployee().getName();
			}
		case 4:
			return StatusTradutor.getLabel(user.getActive());
		case 5:
			return user.isBlocked() ? Messages.getMessage("app.blocked") : Messages.getMessage("app.unblocked");
		default:
			break;
		}
		return null;
	}

}
