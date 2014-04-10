package com.rochatec.athena.humanresources.employee.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.humanresources.employee.editor.EmployeeEditor;
import com.rochatec.athena.humanresources.employee.editor.EmployeeEditorInput;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.util.WorkbenchUtil;

public class EmployeeNewHandler extends AbstractHandler{
	
	public static final String ID = "com.rochatec.metallurgical.humanresources.employee.handler.EmployeeNewHandler";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try{
			EmployeeEditorInput input = new EmployeeEditorInput();
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID,EmployeeEditor.ID,input, event);
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}

}
