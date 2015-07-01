package com.rochatec.pos.athena.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.graphics.util.FontToolkit;
import com.rochatec.pos.athena.dialogs.ExecuteDialog;

public class ExecuteHandler extends DefaultSellHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		fireSellService(window,"sell");
		Shell shell = window.getShell();
		shell.setFont(FontToolkit.getInstance().getTahoma(10,SWT.NONE));
		ExecuteDialog dialog = new ExecuteDialog(window.getShell());
		if (dialog.open() == SWT.OK){
			
		}
		return null;
	}

}
