package com.rochatec.pos.athena.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.pos.athena.preferences.PreferenceBuilder;

public class OpenConfigHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShell(event);		
		PreferenceDialog pd = PreferenceBuilder.getInstance().getDialog(shell);
        pd.open();
		return null;
	}
}
