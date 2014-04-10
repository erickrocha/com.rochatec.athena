package com.rochatec.athena.humanresources.job.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.humanresources.job.editor.JobEditor;
import com.rochatec.athena.humanresources.job.editor.JobEditorInput;
import com.rochatec.athena.model.Job;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.util.WorkbenchUtil;

public class JobEditHandler extends AbstractHandler{
	
	public static final String ID = "com.rochatec.metallurgical.humanresources.job.handler.JobEditHandler";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		Job job = (Job) selection.getFirstElement();
		try{						
			JobEditorInput input = new JobEditorInput(job);			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, JobEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
		
	}

}
