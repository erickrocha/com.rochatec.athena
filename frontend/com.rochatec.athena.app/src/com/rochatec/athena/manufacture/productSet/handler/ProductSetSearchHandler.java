package com.rochatec.athena.manufacture.productSet.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.manufacture.productSet.perspective.ProductSetSearchPerspective;

public class ProductSetSearchHandler extends DefaultCrudHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		fireCrudService(window,"productSet");
		IPerspectiveRegistry registry = window.getWorkbench().getPerspectiveRegistry();
		IWorkbenchPage page = window.getActivePage();
		page.setPerspective(registry.findPerspectiveWithId(ProductSetSearchPerspective.ID));
		return null;
	}
}
