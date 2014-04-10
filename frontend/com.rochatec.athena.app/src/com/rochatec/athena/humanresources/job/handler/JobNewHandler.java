package com.rochatec.athena.humanresources.job.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.humanresources.job.editor.JobEditor;
import com.rochatec.athena.humanresources.job.editor.JobEditorInput;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.util.WorkbenchUtil;

public class JobNewHandler extends DefaultCrudHandler {

	public static final String ID = "com.rochatec.metallurgical.humanresources.job.handler.JobNewHandler";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try{						
			JobEditorInput input = new JobEditorInput();			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, JobEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}	

}
