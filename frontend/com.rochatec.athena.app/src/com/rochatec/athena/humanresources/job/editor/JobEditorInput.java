package com.rochatec.athena.humanresources.job.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Job;
import com.rochatec.athena.util.IPathIcons;

public class JobEditorInput implements IEditorInput {

	private Job job;

	public JobEditorInput(Job job) {
		this.job = job;
	}

	public JobEditorInput() {
		this(new Job());
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
		return Activator.getImageDescriptor(IPathIcons.JOB_16);
	}

	@Override
	public String getName() {
		return Messages.getMessage("job.object");
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return getName();
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}
