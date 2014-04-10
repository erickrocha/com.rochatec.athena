package com.rochatec.athena.manufacture.product.box;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.client.service.ManufactureClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.manufacture.category.dialog.CategoryDialog;
import com.rochatec.athena.model.Category;
import com.rochatec.athena.model.Product;
import com.rochatec.athena.model.Status;
import com.rochatec.athena.provider.StatusLabelProvider;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.event.ColumnChangedEvent;
import com.rochatec.graphics.event.DialogEvent;
import com.rochatec.graphics.event.SearchBoxEvent;
import com.rochatec.graphics.gui.AbstractWrapperSearchBox;
import com.rochatec.graphics.listener.DialogListener;
import com.rochatec.graphics.model.Column;
import com.rochatec.graphics.model.ColumnType;
import com.rochatec.graphics.model.IColumn;
import com.rochatec.graphics.util.IKeyPadConstants;

public class ProductSearchBox extends AbstractWrapperSearchBox<Product>{
	
	protected ManufactureClientService manufactureClientService = ServiceFactory.getInstance().getManufactureClientService();
	protected Category selected;
	
	public ProductSearchBox(Composite parent, Executable<Product> executable) {
		super(parent, executable);
	}

	@Override
	public List<IColumn> getColumns() {
		List<IColumn> columns = new ArrayList<IColumn>();
		columns.add(new Column("id",Messages.getMessage("product.field.label.id"),ColumnType.LONG));
		columns.add(new Column("name",Messages.getMessage("product.field.label.name"),ColumnType.NAME));
		columns.add(new Column("dateRegister",Messages.getMessage("product.field.label.dateRegister"),ColumnType.DATE));
		columns.add(new Column("category",Messages.getMessage("product.field.label.category"),ColumnType.OBJECT));	
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
		 	case OBJECT:
		 		e.box.setEnabledCalendarCombo(true);
		 		e.box.setEnabledCombo(true);
		 		e.box.setEnabledText(true);
		 		break;
		default:
			break;
		}
		
	}

	@Override
	public List<Product> fireSearchBoxActivated(SearchBoxEvent e) {
		Status status = (Status)e.status != null ? (Status)e.status : Status.ALL;
		List<Product> products = new ArrayList<Product>();
		switch (e.column.getSQLType()) {
		case LONG:
			products.clear();
			products.add(manufactureClientService.findProductById(e.searchBox.getLong()));
			break;
		case NAME:
			products = manufactureClientService.findProductsByName(e.value+"%",e.begin,e.end,status);
			break;
		case DATE:
			products = manufactureClientService.findProductsByDateRegister(e.begin,e.end, status);
			break;
		case OBJECT:
			if (selected != null){
				products = manufactureClientService.findProductsByCategory(selected,e.begin,e.end, status);
			}else{
				products = manufactureClientService.findProductsByCategory(e.value,e.begin,e.end,status);	
			}
			break;
		default:
			break;
		}
		return products;
	}
	
	class CategoryDialogListener implements DialogListener{

		@Override
		public void showDialog(DialogEvent event) {
			switch (event.keyCode) {
			case IKeyPadConstants.KEY_F9:
				CategoryDialog dialog = new CategoryDialog(event.display.getActiveShell());
				selected = dialog.dialog();
				event.searchBox.setTextValue(selected != null ? selected.getName() : "");
				break;
			default :
				break;
			}
		}
	}

}
