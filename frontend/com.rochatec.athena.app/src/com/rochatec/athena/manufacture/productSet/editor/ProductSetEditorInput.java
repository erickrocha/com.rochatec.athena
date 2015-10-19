package com.rochatec.athena.manufacture.productSet.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.ProductSet;
import com.rochatec.athena.util.IPathIcons;

public class ProductSetEditorInput implements IEditorInput {

	private ProductSet productSet;

	public ProductSetEditorInput(ProductSet productSet) {
		this.productSet = productSet;
	}

	public ProductSetEditorInput() {
		this(new ProductSet());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getAdapter(Class adapter) {
		return null;
	}

	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return Activator.getImageDescriptor(IPathIcons.PRODUCT_SET_16);
	}

	public String getName() {
		return Messages.getMessage("productset.name");
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return getName();
	}

	public ProductSet getProductSet() {
		return productSet;
	}

}
