package com.rochatec.athena.security.profile.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.model.Profile;
import com.rochatec.athena.provider.DefaultSelectionProvider;
import com.rochatec.athena.security.profile.box.ProfileSearchBox;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.gui.AbstractWrapperSearchBox;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.view.AbstractView;

public class ProfileSearchView extends AbstractView implements Executable<Profile>{

	public static final String ID = "com.rochatec.athena.security.profile.view.ProfileSearchView";
	private AbstractWrapperSearchBox<Profile> searchBox;
	
	@Override
	protected void createContents(Composite parent) {
		searchBox = new ProfileSearchBox(parent,this);		
	}

	@Override
	protected void addListeners() {
		getSite().setSelectionProvider(new DefaultSelectionProvider());
		getSite().getSelectionProvider().addSelectionChangedListener(
				(ISelectionChangedListener) Activator.getDefault().getView(
						ProfileTableView.ID));
	}

	@Override
	public void setFocus() {
		searchBox.setFocus();
	}

	@Override
	public void execute(List<Profile> objects) {
		SearchSelection<Profile> selection = new SearchSelection<Profile>(objects);
		getSite().getSelectionProvider().setSelection(selection);
	}

	@Override
	public void execute(Profile object) {
		
	}

}
