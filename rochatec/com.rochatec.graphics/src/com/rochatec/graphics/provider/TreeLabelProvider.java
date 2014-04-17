package com.rochatec.graphics.provider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;

import com.rochatec.framework.model.TreeObject;
import com.rochatec.graphics.Activator;
import com.rochatec.graphics.util.IComponentsIcons;

public class TreeLabelProvider extends LabelProvider{
	
	@Override
	public String getText(Object element) {		
		return element.toString();
	}
	
	public Image getImage(Object obj) {		
		switch (((TreeObject)obj).getType()) {
		case 0:
			return Activator.getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER).createImage();
		case 1:
			return Activator.getImageDescriptor(IComponentsIcons.INVOICE_16).createImage();
		case 2:
			return Activator.getImageDescriptor(IComponentsIcons.PURCHASE_ORDER_16).createImage();
		}
		return null;
	}

}
