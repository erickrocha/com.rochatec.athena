package com.rochatec.athena.invoice.input.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.rochatec.athena.model.Supplier;
import com.rochatec.framework.model.HierarchyObject;
import com.rochatec.framework.model.TreeParent;
import com.rochatec.graphics.selection.DefaultSelectHandler;

public class InvoiceInputNewHandler extends DefaultSelectHandler{

	private Supplier supplier;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ITreeSelection selection =  (ITreeSelection)HandlerUtil.getCurrentSelection(event);		
		HierarchyObject root = (HierarchyObject)selection.getFirstElement();
		
		
		return null;
	}
		
}
