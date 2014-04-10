package com.rochatec.athena.security.user.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.User;
import com.rochatec.athena.provider.DefaultSelectionProvider;
import com.rochatec.athena.security.user.box.UserSearchBox;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.gui.AbstractWrapperSearchBox;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.view.AbstractView;

public class UserSearchView extends AbstractView implements Executable<User>{

	public static final String ID = "com.rochatec.metallurgical.security.user.view.UserSearchView";
	private AbstractWrapperSearchBox<User> searchBox;
	
	@Override
	protected void createContents(Composite parent) {
		searchBox = new UserSearchBox(parent, this);
		
	}

	@Override
	protected void addListeners() {
		getSite().setSelectionProvider(new DefaultSelectionProvider());
		getSite().getSelectionProvider().addSelectionChangedListener(
				(ISelectionChangedListener) Activator.getDefault().getView(
						UserTableView.ID));		
	}

	@Override
	public void setFocus() {
		searchBox.setFocus();
	}

	@Override
	public void execute(List<User> objects) {
		SearchSelection<User> selection = new SearchSelection<User>(objects);
		getSite().getSelectionProvider().setSelection(selection);		
	}

	@Override
	public void execute(User object) {
		
	}

}
