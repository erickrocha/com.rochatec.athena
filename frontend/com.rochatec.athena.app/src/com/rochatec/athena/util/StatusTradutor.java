package com.rochatec.athena.util;


import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.InvoiceStatus;
import com.rochatec.athena.model.SaleOrderStatus;
import com.rochatec.athena.model.Status;

public class StatusTradutor {

	public static String getLabel(Status status){
		switch (status) {
		case ALL:
			return Messages.getMessage("app.status."+Status.ALL.toString());
		case ACTIVE:
			return Messages.getMessage("app.status."+Status.ACTIVE.toString());
		case INACTIVE:
			return Messages.getMessage("app.status."+Status.INACTIVE.toString());
		default:
			return "";
		}
	}
	
	public static String getLabel(SaleOrderStatus status){
		switch (status) {
		case ALL:
			return Messages.getMessage("saleOrder.status."+SaleOrderStatus.ALL.toString());
		case SAVE:
			return Messages.getMessage("saleOrder.status."+SaleOrderStatus.SAVE.toString());
		case PROCESS:
			return Messages.getMessage("saleOrder.status."+SaleOrderStatus.PROCESS.toString());
		case GENERATED:
			return Messages.getMessage("saleOrder.status."+SaleOrderStatus.GENERATED.toString());
		case FINISHED:
			return Messages.getMessage("saleOrder.status."+SaleOrderStatus.FINISHED.toString());
		default:
			return "";
		}
	}
	
	public static String getLabel(InvoiceStatus status){
		switch (status) {
		case ALL:
			return Messages.getMessage("invoice.status."+InvoiceStatus.ALL.toString());
		case SAVE:
			return Messages.getMessage("invoice.status."+InvoiceStatus.SAVE.toString());
		case FINISHED:
			return Messages.getMessage("invoice.status."+InvoiceStatus.FINISHED.toString());
		case GENERATED:
			return Messages.getMessage("invoice.status."+InvoiceStatus.GENERATED.toString());
		case PRINTED:
			return Messages.getMessage("invoice.status."+InvoiceStatus.PRINTED.toString());
		default:
			return "";
		}
	}
}
