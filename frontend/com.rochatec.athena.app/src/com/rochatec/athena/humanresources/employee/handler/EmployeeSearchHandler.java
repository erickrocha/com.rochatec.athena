package com.rochatec.athena.humanresources.employee.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.humanresources.employee.perspective.EmployeeSearchPerspective;

public class EmployeeSearchHandler extends DefaultCrudHandler{
	
	public static final String ID = "com.rochatec.metallurgical.humanresources.employee.handler.EmployeeSearchHandler";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		fireCrudService(window,"employee");
		IPerspectiveRegistry registry = window.getWorkbench().getPerspectiveRegistry();
		IWorkbenchPage page = window.getActivePage();
		page.setPerspective(registry.findPerspectiveWithId(EmployeeSearchPerspective.ID));
		return null;
	}

}
