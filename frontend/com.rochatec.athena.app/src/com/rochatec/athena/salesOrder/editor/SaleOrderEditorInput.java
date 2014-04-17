package com.rochatec.athena.salesOrder.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.SaleOrder;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.framework.util.Message;

public class SaleOrderEditorInput implements IEditorInput{
	
	private SaleOrder saleOrder;
	
	public SaleOrderEditorInput(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
	}
	
	public SaleOrderEditorInput() {
		this(new SaleOrder());
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
		return Activator.getImageDescriptor(IPathIcons.SALE_ORDER_16);
	}

	@Override
	public String getName() {		
		return Message.getMessage("saleOrder.name");
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return getName();
	}

	public SaleOrder getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
	}
	
}
