package com.rochatec.athena.util;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

public class WorkbenchUtil {

	public static void handlerOpenEditorInPerspective(String perspectiveId,
			String editorId, IEditorInput input, ExecutionEvent event)
			throws PartInitException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IPerspectiveRegistry registry = window.getWorkbench()
				.getPerspectiveRegistry();
		IWorkbenchPage page = window.getActivePage();
		page.setPerspective(registry.findPerspectiveWithId(perspectiveId));
		page.openEditor(input, editorId);
	}
	
	public static void handlerOpenEditorInPerspective(String perspectiveId,
			String editorId, IEditorInput input, IWorkbenchWindow workbenchWindow)
			throws PartInitException {
		IWorkbenchWindow window = workbenchWindow;
		IPerspectiveRegistry registry = window.getWorkbench()
				.getPerspectiveRegistry();
		IWorkbenchPage page = window.getActivePage();
		page.setPerspective(registry.findPerspectiveWithId(perspectiveId));
		page.openEditor(input, editorId);
	}
	
	

}
