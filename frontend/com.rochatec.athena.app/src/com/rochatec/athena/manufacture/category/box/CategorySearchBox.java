package com.rochatec.athena.manufacture.category.box;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.client.service.ManufactureClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Category;
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

public class CategorySearchBox extends AbstractWrapperSearchBox<Category>{
	
	protected ManufactureClientService manufactureClientService = ServiceFactory.getInstance().getManufactureClientService();
	
	public CategorySearchBox(Composite parent, Executable<Category> executable) {
		super(parent, executable);		
	}

	@Override
	public List<IColumn> getColumns() {
		List<IColumn> columns = new ArrayList<IColumn>();
		columns.add(new Column("id",Messages.getMessage("category.field.label.id"),ColumnType.LONG));
		columns.add(new Column("name",Messages.getMessage("category.field.label.name"),ColumnType.STRING));
		columns.add(new Column("all",Messages.getMessage("category.field.label.all"),ColumnType.NONE));
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
	public List<Category> fireSearchBoxActivated(SearchBoxEvent e) {
		List<Category> categories = new ArrayList<Category>();
		switch (e.column.getSQLType()) {
		case LONG:
			categories.clear();
			categories.add(manufactureClientService.findCategoryById(e.searchBox.getLong()));						
			break;			
		case STRING:				
			categories =manufactureClientService.findCategoriesByName(e.value+"%");
			break;
		case NONE:
			categories = manufactureClientService.findAllCategories();
			break;
		default:
			break;
		}
		return categories;
	}
	
	
	
}
