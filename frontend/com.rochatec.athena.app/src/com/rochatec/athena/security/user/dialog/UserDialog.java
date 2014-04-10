package com.rochatec.athena.security.user.dialog;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.User;
import com.rochatec.athena.security.user.box.UserSearchBox;
import com.rochatec.athena.security.user.provider.UserLabelProvider;
import com.rochatec.athena.security.user.table.UserTable;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.dialog.AbstractDialog;
import com.rochatec.graphics.provider.GenericContentProvider;

public class UserDialog extends AbstractDialog<User>implements Executable<User>{
	

	public UserDialog(Shell owner) {
		super(owner);
	}

	@Override
	public void createSearchArea(Composite parent) {
		new UserSearchBox(parent,this);		
	}

	@Override
	public void createTable(Composite parent) {
		table = new UserTable(parent);
		table.setContentProvider(new GenericContentProvider<User>());
		table.setLabelProvider(new UserLabelProvider());
	}

	@Override
	public String getTitle() {
		return Messages.getMessage("user.dialog.tile");
	}

	@Override
	public void execute(List<User> objects) {
		table.setInput(objects);		
	}

	@Override
	public void execute(User object) {
		
	}

}
