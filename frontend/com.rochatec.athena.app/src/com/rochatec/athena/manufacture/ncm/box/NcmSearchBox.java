package com.rochatec.athena.manufacture.ncm.box;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.client.service.ManufactureClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Ncm;
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

public class NcmSearchBox extends AbstractWrapperSearchBox<Ncm>{
	
	protected ManufactureClientService manufactureClientService = ServiceFactory.getInstance().getManufactureClientService();
	
	public NcmSearchBox(Composite parent, Executable<Ncm> executable) {
		super(parent, executable);
	}

	@Override
	public List<IColumn> getColumns() {
		List<IColumn> columns = new ArrayList<IColumn>();
		columns.add(new Column("code",Messages.getMessage("ncm.field.label.code"),ColumnType.LONG));
		columns.add(new Column("description",Messages.getMessage("ncm.field.label.description"),ColumnType.STRING));
		columns.add(new Column("all",Messages.getMessage("ncm.field.label.all"),ColumnType.NONE));
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
		case NONE:
			e.box.setEnabledText(false);
			break;
		default:
			e.box.setEnabledText(true);
			break;
		}	
		
	}

	@Override
	public List<Ncm> fireSearchBoxActivated(SearchBoxEvent e) {
		List<Ncm> ncms = new ArrayList<Ncm>();
		switch (e.column.getSQLType()) {
		case LONG:
			ncms.clear();
			ncms.add(manufactureClientService.findNcmById(e.value));						
			break;			
		case STRING:				
			ncms =manufactureClientService.findNcmsByName(e.value+"%");
			break;
		case NONE:
			ncms = manufactureClientService.findAllNcms();
			break;
		default:
			break;
		}
		return ncms;
	}

	
	
}
