package com.rochatec.athena.invoice.output.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.rochatec.athena.invoice.output.view.InvoiceOutputCustomerHistoryView;
import com.rochatec.athena.invoice.output.view.InvoiceOutputCustomerView;

public class InvoiceOutputPerspective implements IPerspectiveFactory{
	
	public static final String ID = "com.rochatec.athena.invoice.output.perspective.InvoiceOutputPerspective";
	private static final String CUSTOMER_PART = "CUSTOMER_PART";
	private static final String INVOICE_INPUT__PART = "INVOICE_INPUT__PART";			

	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();		
		
		IFolderLayout customerPart = layout.createFolder(CUSTOMER_PART,IPageLayout.LEFT,0.25f,editorArea);
		customerPart.addView(InvoiceOutputCustomerView.ID);
		
		IFolderLayout invoiceInputPart = layout.createFolder(INVOICE_INPUT__PART,IPageLayout.BOTTOM,0.5f,CUSTOMER_PART);
		invoiceInputPart.addView(InvoiceOutputCustomerHistoryView.ID);
		
	}
}
