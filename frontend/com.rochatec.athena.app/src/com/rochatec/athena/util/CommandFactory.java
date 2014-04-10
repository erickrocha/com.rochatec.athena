package com.rochatec.athena.util;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.handlers.IHandlerService;

public class CommandFactory {

	public static IDoubleClickListener getDoubleClickCommand(final String commandId, final IWorkbenchPartSite site) {
		IDoubleClickListener listener = new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IHandlerService handlerService = (IHandlerService) site.getService(IHandlerService.class);
				try {
					handlerService.executeCommand(commandId, null);
				} catch (Exception ex) {
					throw new RuntimeException(commandId + " not found");
				}
			}
		};
		return listener;
	}

	public static final void executeCommand(final String commandId,
			final IWorkbenchPartSite site) {
		IHandlerService handlerService = (IHandlerService) site.getService(IHandlerService.class);
		try {
			handlerService.executeCommand(commandId, null);
		} catch (Exception ex) {
			throw new RuntimeException(commandId + " not found");
		}
	}

}
