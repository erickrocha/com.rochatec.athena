package com.rochatec.athena.supply.shipper.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Shipper;
import com.rochatec.athena.util.IPathIcons;

public class ShipperEditorInput implements IEditorInput {

	private Shipper shipper;

	public ShipperEditorInput(Shipper shipper) {
		this.shipper = shipper;
	}

	public ShipperEditorInput() {
		this(new Shipper());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getAdapter(Class adapter) {
		
		return null;
	}

	@Override
	public boolean exists() {
		
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return Activator.getImageDescriptor(IPathIcons.SHIPPER_16);
	}

	@Override
	public String getName() {
		return Messages.getMessage("shipper.name");
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return getName();
	}

	public Shipper getShipper() {
		return shipper;
	}

	public void setShipper(Shipper shipper) {
		this.shipper = shipper;
	}

}
