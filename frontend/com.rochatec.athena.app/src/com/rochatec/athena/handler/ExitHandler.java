package com.rochatec.athena.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.i18n.Messages;

public class ExitHandler extends AbstractHandler{
	
	public static final String ID = "com.rochatec.metallurgical.handler.ExitHandler";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		MessageBox msg = new MessageBox(HandlerUtil.getActiveShell(event),SWT.ICON_QUESTION|SWT.YES|SWT.NO);
   		msg.setText(Messages.getMessage("app.dialog.close.title"));
   		msg.setMessage(Messages.getMessage("app.dialog.close.message"));
   		int resp = msg.open();
   		if (resp == SWT.YES){
   			HandlerUtil.getActiveWorkbenchWindow(event).close();
   		}
   		return null;
	}

}
