package com.rochatec.athena.humanresources.job.dialog;

import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.humanresources.job.box.JobSearchBox;
import com.rochatec.athena.humanresources.job.provider.JobLabelProvider;
import com.rochatec.athena.humanresources.job.table.JobTable;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Job;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.dialog.AbstractDialog;
import com.rochatec.graphics.provider.GenericContentProvider;

public class JobDialog extends AbstractDialog<Job> implements Executable<Job> {

	public JobDialog(Shell owner) {
		super(owner);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(800, 600);
	}

	@Override
	public void createSearchArea(Composite parent) {
		new JobSearchBox(parent, this);
	}

	@Override
	public void createTable(Composite parent) {
		table = new JobTable(parent);
		table.setContentProvider(new GenericContentProvider<Job>());
		table.setLabelProvider(new JobLabelProvider());
	}

	@Override
	public String getTitle() {
		return Messages.getMessage("job.titles");
	}

	public void execute(List<Job> jobs) {
		table.setInput(jobs);
	}

	@Override
	public void execute(Job object) {
		
	}

}
