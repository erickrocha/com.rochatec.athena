package com.rochatec.athena.manufacture.product.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.util.IPathIcons;

public class ProductEditorInput implements IEditorInput {

	private Product product;

	public ProductEditorInput() {
		this(new Product());
	}

	public ProductEditorInput(Product product) {
		this.product = product;
	}

	@SuppressWarnings("rawtypes")
	
	public Object getAdapter(Class arg0) {
		return null;
	}

	
	public boolean exists() {
		return false;
	}

	
	public ImageDescriptor getImageDescriptor() {
		return Activator.getImageDescriptor(IPathIcons.PRODUCT_16);
	}

	
	public String getName() {
		return Messages.getMessage("product.name");
	}

	
	public IPersistableElement getPersistable() {
		return null;
	}

	
	public String getToolTipText() {
		return getName();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
