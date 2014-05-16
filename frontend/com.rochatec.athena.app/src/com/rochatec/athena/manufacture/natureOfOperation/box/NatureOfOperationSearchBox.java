package com.rochatec.athena.manufacture.natureOfOperation.box;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.client.service.ResourceClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.NatureOfOperation;
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

public class NatureOfOperationSearchBox extends AbstractWrapperSearchBox<NatureOfOperation>{

	public NatureOfOperationSearchBox(Composite parent,
			Executable<NatureOfOperation> executable) {
		super(parent, executable);
	}

	protected ResourceClientService resourceClientService = ServiceFactory.getInstance().getResourceClientService();
	
	@Override
	public List<IColumn> getColumns() {
		List<IColumn> columns = new ArrayList<IColumn>();
		columns.add(new Column("cfop",Messages.getMessage("natureofoperation.field.label.cfop"),ColumnType.LONG));
		columns.add(new Column("all",Messages.getMessage("natureofoperation.field.label.all"),ColumnType.NONE));
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
	public List<NatureOfOperation> fireSearchBoxActivated(SearchBoxEvent e) {
		List<NatureOfOperation> rows = new ArrayList<NatureOfOperation>();
		switch (e.column.getSQLType()) {
		case LONG:
			rows.clear();
			rows.add(resourceClientService.findNatureOperationByCode(e.value));						
			break;			
		case NONE:
			rows = resourceClientService.findNatureOperationHierarchy();
			break;
		default:
			break;
		}
		return rows;
	}

}
