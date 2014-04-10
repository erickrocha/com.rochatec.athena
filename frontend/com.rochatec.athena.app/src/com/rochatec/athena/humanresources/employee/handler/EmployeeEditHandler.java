package com.rochatec.athena.humanresources.employee.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.humanresources.employee.editor.EmployeeEditor;
import com.rochatec.athena.humanresources.employee.editor.EmployeeEditorInput;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.perspective.InputPerspective;
import com.rochatec.athena.util.WorkbenchUtil;

public class EmployeeEditHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection)HandlerUtil.getCurrentSelection(event);
		Employee employee = (Employee) selection.getFirstElement();
		try{						
			EmployeeEditorInput input = new EmployeeEditorInput(employee);			
			WorkbenchUtil.handlerOpenEditorInPerspective(InputPerspective.ID, EmployeeEditor.ID,input, event);			
		}catch (PartInitException ex){
			Activator.getDefault().addConsoleError(ex);
		}
		return null;
	}

}
