package com.rochatec.athena.invoice.item.Listener;

import com.rochatec.athena.invoice.item.event.InvoiceItemEvent;

public interface InvoiceItemListener {

	public void itemAdded(InvoiceItemEvent itemEvent);
	
	public void iItemUpdated(InvoiceItemEvent itemEvent);
	
	public void itemDeleted(InvoiceItemEvent itemEvent);
	
}
