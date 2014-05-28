package com.rochatec.athena.ui.internal.commands;

import org.eclipse.core.commands.AbstractParameterValueConverter;
import org.eclipse.core.commands.ParameterValueConversionException;

import com.rochatec.athena.client.service.InvoiceClientService;
import com.rochatec.athena.model.InvoiceInput;
import com.rochatec.athena.utils.ServiceFactory;

public class InvoiceCommandParameterType extends AbstractParameterValueConverter{

	private InvoiceClientService invoiceClientService = ServiceFactory.getInstance().getInvoiceClientService();
	
	@Override
	public Object convertToObject(String parameterValue)
			throws ParameterValueConversionException {
		Long id = Long.parseLong(parameterValue);
		InvoiceInput invoice = invoiceClientService.findInvoiceInputById(id);
		return invoice;
	}

	@Override
	public String convertToString(Object parameterValue)
			throws ParameterValueConversionException {
		InvoiceInput invoiceInput = (InvoiceInput)parameterValue;
		return invoiceInput.getId().toString();
	}

}
