package com.rochatec.athena.supply.supplier.box;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.client.service.SupplyClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Status;
import com.rochatec.athena.model.Supplier;
import com.rochatec.athena.provider.StatusLabelProvider;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.event.ColumnChangedEvent;
import com.rochatec.graphics.event.SearchBoxEvent;
import com.rochatec.graphics.gui.AbstractWrapperSearchBox;
import com.rochatec.graphics.model.Column;
import com.rochatec.graphics.model.ColumnType;
import com.rochatec.graphics.model.IColumn;

public class SupplierSearchBox extends AbstractWrapperSearchBox<Supplier>{

	protected SupplyClientService supplyClientService = ServiceFactory.getInstance().getSupplyClientService();
	
	public SupplierSearchBox(Composite parent, Executable<Supplier> executable) {
		super(parent, executable);
	}

	@Override
	public List<IColumn> getColumns() {
		List<IColumn> columns = new ArrayList<IColumn>();
		columns.add(new Column("id",Messages.getMessage("supplier.field.label.id"),ColumnType.LONG));
		columns.add(new Column("businessName",Messages.getMessage("supplier.field.label.businessName"),ColumnType.NAME));
		columns.add(new Column("socialSecurity",Messages.getMessage("supplier.field.label.socialSecurity"),ColumnType.STRING));
		columns.add(new Column("dateRegister",Messages.getMessage("supplier.field.label.dateRegister"),ColumnType.DATE));				
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
			break;
		}
		
	}

	@Override
	public List<Supplier> fireSearchBoxActivated(SearchBoxEvent e) {
		Status status = (Status)e.status != null ? (Status)e.status : Status.ALL;
		List<Supplier> suppliers = new ArrayList<Supplier>();
		switch (e.column.getSQLType()) {
		case LONG:
			suppliers.clear();
			suppliers.add(supplyClientService.findSupplierByIndex(e.searchBox.getLong(),e.value));
			break;
		case NAME:
			suppliers = supplyClientService.findSuppliersByName(e.value,e.begin,e.end,status);
			break;
		case STRING:
			suppliers.clear();
			suppliers.add(supplyClientService.findSupplierBySocialSecurity(e.value));
			break;
		case DATE:
			suppliers = supplyClientService.findBySuppliersDateRegister(e.begin,e.end,status);
			break;
		default:
			break;
		}
		return suppliers;
	}

}
