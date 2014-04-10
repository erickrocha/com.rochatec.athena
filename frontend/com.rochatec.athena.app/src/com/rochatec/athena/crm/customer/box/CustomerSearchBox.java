package com.rochatec.athena.crm.customer.box;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.client.service.CRMClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Customer;
import com.rochatec.athena.model.Status;
import com.rochatec.athena.provider.StatusLabelProvider;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.event.ColumnChangedEvent;
import com.rochatec.graphics.event.SearchBoxEvent;
import com.rochatec.graphics.gui.AbstractWrapperSearchBox;
import com.rochatec.graphics.model.Column;
import com.rochatec.graphics.model.ColumnType;
import com.rochatec.graphics.model.IColumn;

public class CustomerSearchBox extends AbstractWrapperSearchBox<Customer>{
	
	protected CRMClientService crmClientService = ServiceFactory.getInstance().getCrmClientService();
	
	public CustomerSearchBox(Composite parent,Executable<Customer> executable) {
		super(parent, executable);
	}
	
	@Override
	public List<IColumn> getColumns() {
		List<IColumn> columns = new ArrayList<IColumn>();
		columns.add(new Column("id",Messages.getMessage("customer.field.label.id"),ColumnType.LONG));
		columns.add(new Column("name",Messages.getMessage("customer.field.label.name"),ColumnType.NAME));
		columns.add(new Column("socialSecurity",Messages.getMessage("customer.field.label.socialSecurity"),ColumnType.STRING));
		columns.add(new Column("dateRegister",Messages.getMessage("customer.field.label.dateRegister"),ColumnType.DATE));				
		return columns;
	}

	@Override
	public IBaseLabelProvider getLabelProvider() {
		return new StatusLabelProvider();
	}

	@Override
	public Object getStatus() {
		return Status.getAll();
	}

	@Override
	public void fireColumnsChanged(ColumnChangedEvent e) {
		e.box.clear();
		e.box.setLabelValue(e.column.getLabel());
		switch (e.column.getSQLType()) {
		case LONG:
			e.box.setEnabledCalendarCombo(false);
			e.box.setEnabledCombo(false);
			e.box.setEnabledText(true);
			break;
		case NAME:
			e.box.setEnabledCalendarCombo(true);
			e.box.setEnabledCombo(true);
			e.box.setEnabledText(true);
			break;
		case STRING:
			e.box.setEnabledCalendarCombo(false);
			e.box.setEnabledCombo(false);
			e.box.setEnabledText(true);
			break;
		case DATE:
			e.box.setEnabledCalendarCombo(true);
			e.box.setEnabledCombo(true);
			e.box.setEnabledText(false);
			break;			
		default:
			e.box.setEnabledText(true);
			break;
		}
	}

	@Override
	public List<Customer> fireSearchBoxActivated(SearchBoxEvent e) {
		Status status = (Status)e.status != null ? (Status)e.status : Status.ALL;
		List<Customer> customers = new ArrayList<Customer>();
		switch (e.column.getSQLType()) {
		case LONG:
			customers.clear();
			customers.add(crmClientService.findCustomerById(e.searchBox.getLong()));						
			break;
		case NAME:
			customers = crmClientService.findCustomersByName(e.value, e.begin, e.end, status);
			break;			
		case STRING:
			customers.clear();
			customers.add(crmClientService.findCustomerBySocialSecurity(e.value));
			break;
		case DATE:
			customers = crmClientService.findCustomersByDateRegister(e.begin, e.end, status);
			break;
		default:
			break;
		}
		return customers;
	}	
	
}
