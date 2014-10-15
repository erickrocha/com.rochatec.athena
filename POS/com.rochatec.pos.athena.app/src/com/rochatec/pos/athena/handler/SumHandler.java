package com.rochatec.pos.athena.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.pos.athena.perspective.SumPerspective;

public class SumHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IPerspectiveRegistry registry = window.getWorkbench()
				.getPerspectiveRegistry();
		IWorkbenchPage page = window.getActivePage();
		page.setPerspective(registry.findPerspectiveWithId(SumPerspective.ID));		
		return null;
	}

	
}
