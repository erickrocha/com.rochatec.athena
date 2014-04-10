package com.rochatec.athena.manufacture.category.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.manufacture.category.box.CategorySearchBox;
import com.rochatec.athena.model.Category;
import com.rochatec.athena.provider.DefaultSelectionProvider;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.gui.AbstractWrapperSearchBox;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.view.AbstractView;

public class CategorySearchView extends AbstractView implements Executable<Category>{
	
	public static final String ID = "com.rochatec.athena.manufacture.category.view.CategorySearchView";
	private AbstractWrapperSearchBox<Category> searchBox;
	
	
	@Override
	public String getPartName() {
		return Messages.getMessage("category.view.search.title");
	}
	
	@Override
	public void execute(List<Category> categories) {
		SearchSelection<Category> selection = new SearchSelection<Category>(categories);
		getSite().getSelectionProvider().setSelection(selection);
	}

	@Override
	protected void createContents(Composite parent) {
		searchBox = new CategorySearchBox(parent,this);
	}

	@Override
	protected void addListeners() {
		getSite().setSelectionProvider(new DefaultSelectionProvider());
		getSite().getSelectionProvider().addSelectionChangedListener(
				(ISelectionChangedListener) Activator.getDefault().getView(
						CategoryTableView.ID));
	}

	@Override
	public void setFocus() {
		searchBox.setFocus();
	}

	@Override
	public void execute(Category object) {
		
	}

}
