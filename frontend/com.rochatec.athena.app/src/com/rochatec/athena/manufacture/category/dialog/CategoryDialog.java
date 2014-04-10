package com.rochatec.athena.manufacture.category.dialog;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.manufacture.category.box.CategorySearchBox;
import com.rochatec.athena.manufacture.category.provider.CategoryLabelProvider;
import com.rochatec.athena.manufacture.category.table.CategoryTable;
import com.rochatec.athena.model.Category;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.dialog.AbstractDialog;
import com.rochatec.graphics.provider.GenericContentProvider;

public class CategoryDialog extends AbstractDialog<Category> implements Executable<Category>{

	public CategoryDialog(Shell owner) {
		super(owner);
	}

	@Override
	public void execute(List<Category> objects) {
		table.setInput(objects);
	}

	@Override
	public void createSearchArea(Composite parent) {
		new CategorySearchBox(parent,this);		
	}

	@Override
	public void createTable(Composite parent) {
		table = new CategoryTable(parent);
		table.setContentProvider(new GenericContentProvider<Category>());
		table.setLabelProvider(new CategoryLabelProvider());
	}

	@Override
	public String getTitle() {
		return Messages.getMessage("category.dialog.title");
	}

	@Override
	public void execute(Category object) {
		
	}

}
