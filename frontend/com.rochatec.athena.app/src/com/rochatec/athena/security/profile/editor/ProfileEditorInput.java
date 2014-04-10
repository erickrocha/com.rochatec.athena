package com.rochatec.athena.security.profile.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Profile;
import com.rochatec.athena.util.IPathIcons;

public class ProfileEditorInput implements IEditorInput {

	private Profile profile;

	public ProfileEditorInput(Profile profile) {
		this.profile = profile;
	}

	public ProfileEditorInput() {
		this(new Profile());
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
		return Activator.getImageDescriptor(IPathIcons.PROFILE_16);
	}

	@Override
	public String getName() {
		return Messages.getMessage("profile.name");
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return getName();
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}