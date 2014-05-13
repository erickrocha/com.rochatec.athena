package com.rochatec.athena.handler;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.framework.model.Property;
import com.rochatec.graphics.dialog.PerspectiveDialog;

public class ListPerspectivesHandler extends AbstractHandler{

	public static final String ID = "com.rochatec.athena.handler.ListPerspectivesHandler";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		
		Map<String,Property> perspectives = new HashMap<String,Property>();
		int index = 0;
		for (IPerspectiveDescriptor perspectiveDescriptor : window.getWorkbench().getPerspectiveRegistry().getPerspectives()){
			 Property property = new Property(perspectiveDescriptor.getId(),perspectiveDescriptor.getLabel(), index);
			 perspectives.put(perspectiveDescriptor.getId(),property);
			 index++;
		};
		PerspectiveDialog dialog = new PerspectiveDialog(window.getShell(),perspectives);
		Property property = dialog.show();
		
		IPerspectiveRegistry registry = window.getWorkbench().getPerspectiveRegistry();
		IWorkbenchPage page = window.getActivePage();
		page.setPerspective(registry.findPerspectiveWithId(property.getKey()));		
		return null;
	}

}
