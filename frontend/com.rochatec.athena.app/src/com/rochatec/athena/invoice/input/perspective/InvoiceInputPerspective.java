package com.rochatec.athena.invoice.input.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.rochatec.athena.invoice.input.view.InvoiceInputSupplierHistoryView;
import com.rochatec.athena.invoice.input.view.InvoiceInputSupplierView;

public class InvoiceInputPerspective implements IPerspectiveFactory{
	
	public static final String ID = "com.rochatec.athena.invoice.input.perspective.InvoiceInputPerspective";
	private static final String SUPPLIER_PART = "SUPPLIER_PART";
	private static final String INVOICE_INPUT__PART = "INVOICE_INPUT__PART";			

	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();		
		
		IFolderLayout customerPart = layout.createFolder(SUPPLIER_PART,IPageLayout.LEFT,0.25f,editorArea);
		customerPart.addView(InvoiceInputSupplierView.ID);
		
		IFolderLayout invoiceInputPart = layout.createFolder(INVOICE_INPUT__PART,IPageLayout.BOTTOM,0.5f,SUPPLIER_PART);
		invoiceInputPart.addView(InvoiceInputSupplierHistoryView.ID);
		
	}
}
