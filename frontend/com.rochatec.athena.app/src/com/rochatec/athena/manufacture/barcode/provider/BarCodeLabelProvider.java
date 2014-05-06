package com.rochatec.athena.manufacture.barcode.provider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.BarCode;
import com.rochatec.athena.util.IPathIcons;

public class BarCodeLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		return Activator.getImageDescriptor(IPathIcons.INFRA_BARCODE_24)
				.createImage();
	}

	@Override
	public String getText(Object element) {
		BarCode barCode = (BarCode) element;
		return barCode.getBarcode();
	}
}
