package com.rochatec.athena.humanresources.job.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionService;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.humanresources.job.box.JobSearchBox;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Job;
import com.rochatec.athena.provider.DefaultSelectionProvider;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.view.AbstractView;

public class JobSearchView extends AbstractView implements Executable<Job>{

	public static final String ID = "com.rochatec.metallurgical.humanresources.job.view.JobSearchView";
	private JobSearchBox searchBox;
	
	@Override
	public String getPartName() {
		return Messages.getMessage("job.view.search.title");
	}

	private void createSearchBox(Composite parent) {
		searchBox = new JobSearchBox(parent,this);
	}

	@Override
	protected void createContents(Composite parent) {
		createSearchBox(parent);
	}

	public void execute(List<Job> jobs) {
		SearchSelection<Job> selection = new SearchSelection<Job>(jobs);
		getSite().getSelectionProvider().setSelection(selection);
	}

	public ISelectionService getSelectionService() {
		return getSite().getWorkbenchWindow().getSelectionService();
	}	

	@Override
	protected void addListeners() {
		getSite().setSelectionProvider(new DefaultSelectionProvider());
		getSite().getSelectionProvider().addSelectionChangedListener(
				(ISelectionChangedListener) Activator.getDefault().getView(
						JobTableView.ID));
	}

	@Override
	public void setFocus() {
		searchBox.setFocus();
	}

	@Override
	public void execute(Job object) {
		
	}

}