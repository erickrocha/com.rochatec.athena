package com.rochatec.athena.humanresources.job.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.model.Job;

public class JobDeleteHandler extends DefaultCrudHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		Job job = (Job) selection.getFirstElement();
		MessageDialog.openConfirm(HandlerUtil.getActiveShell(event),"Excluir","Excluido "+job.getName());
		return null;
	}

}
