package com.rochatec.athena.invoice.input.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.rochatec.athena.invoice.input.view.InvoiceInputSupplierView;
import com.rochatec.athena.salesOrder.view.SaleOrderHistoryView;

public class InvoiceInputPerspective implements IPerspectiveFactory{
	
	public static final String ID = "com.rochatec.athena.invoice.input.perspective.InvoiceInputPerspective";
	private static final String CUSTOMER_PART = "CUSTOMER_PART";
	private static final String SALE_ORDER_PART = "SALE_ORDER_PART";			

	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();		
		
		IFolderLayout customerPart = layout.createFolder(CUSTOMER_PART,IPageLayout.LEFT,0.25f,editorArea);
		customerPart.addView(InvoiceInputSupplierView.ID);
		
		IFolderLayout saleOrderPart = layout.createFolder(SALE_ORDER_PART,IPageLayout.BOTTOM,0.5f,CUSTOMER_PART);
		saleOrderPart.addView(SaleOrderHistoryView.ID);
		
	}
}
