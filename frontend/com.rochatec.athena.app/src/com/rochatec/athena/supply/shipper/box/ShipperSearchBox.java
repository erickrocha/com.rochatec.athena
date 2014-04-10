package com.rochatec.athena.supply.shipper.box;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.client.service.SupplyClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Shipper;
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

public class ShipperSearchBox extends AbstractWrapperSearchBox<Shipper>{
	
	protected SupplyClientService supplyClientService = ServiceFactory.getInstance().getSupplyClientService();
	
	public ShipperSearchBox(Composite parent, Executable<Shipper> executable) {
		super(parent, executable);
	}

	@Override
	public List<IColumn> getColumns() {
		List<IColumn> columns = new ArrayList<IColumn>();
		columns.add(new Column("id",Messages.getMessage("shipper.field.label.id"),ColumnType.LONG));
		columns.add(new Column("businessName",Messages.getMessage("shipper.field.label.businessName"),ColumnType.NAME));
		columns.add(new Column("socialSecurity",Messages.getMessage("shipper.field.label.socialSecurity"),ColumnType.LONG));
		columns.add(new Column("dateRegister",Messages.getMessage("shipper.field.label.dateRegister"),ColumnType.DATE));	
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
	public List<Shipper> fireSearchBoxActivated(SearchBoxEvent e) {
		Status status = (Status)e.status != null ? (Status)e.status : Status.ALL;
		List<Shipper> shippers = new ArrayList<Shipper>();
		switch (e.column.getSQLType()) {
		case LONG:
			shippers.clear();
			shippers.add(supplyClientService.findShipperByIdOrSocialSecurity(e.searchBox.getLong(),e.value));
			break;
		case NAME:
			shippers = supplyClientService.findShippersByName(e.value,e.begin,e.end,status);
			break;	
		case DATE:
			shippers = supplyClientService.findByShippersDateRegister(e.begin,e.end,status);
			break;
		default:
			break;
		}
		return shippers;
	}

}
