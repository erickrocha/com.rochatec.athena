package com.rochatec.athena.supply.shipper.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.handler.DefaultCrudHandler;
import com.rochatec.athena.supply.shipper.perspective.ShipperSearchPerspective;

public class ShipperSearchHandler extends DefaultCrudHandler{
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		fireCrudService(window,"shipper");
		IPerspectiveRegistry registry = window.getWorkbench().getPerspectiveRegistry();
		IWorkbenchPage page = window.getActivePage();
		page.setPerspective(registry.findPerspectiveWithId(ShipperSearchPerspective.ID));
		return null;
	}
}
