package com.rochatec.athena.manufacture.category.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Category;
import com.rochatec.athena.util.IPathIcons;

public class CategoryEditorInput implements IEditorInput{

	private Category category;
	
	public CategoryEditorInput(Category category) {
		this.category = category;
	}
	
	public CategoryEditorInput() {
		this(new Category());
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	public Object getAdapter(Class adapter) {
		return null;
	}

	
	public boolean exists() {
		return false;
	}

	
	public ImageDescriptor getImageDescriptor() {
		return Activator.getImageDescriptor(IPathIcons.CATEGORY_16);
	}

	
	public String getName() {
		return Messages.getMessage("category.title");
	}

	
	public IPersistableElement getPersistable() {
		return null;
	}

	
	public String getToolTipText() {
		return getName();
	}
	
	public Category getCategory(){
		return category;
	}

}
