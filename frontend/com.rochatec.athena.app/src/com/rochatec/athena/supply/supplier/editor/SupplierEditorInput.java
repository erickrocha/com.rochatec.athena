package com.rochatec.athena.supply.supplier.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.util.IPathIcons;

public class SupplierEditorInput implements IEditorInput {

	private Supplier supplier;

	public SupplierEditorInput(Supplier supplier) {
		this.supplier = supplier;
	}

	public SupplierEditorInput() {
		this(new Supplier());
	}

	@SuppressWarnings("rawtypes")
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
		return Activator.getImageDescriptor(IPathIcons.SUPPLIER_16);
	}

	@Override
	public String getName() {
		return Messages.getMessage("supplier.name");
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return getName();
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
