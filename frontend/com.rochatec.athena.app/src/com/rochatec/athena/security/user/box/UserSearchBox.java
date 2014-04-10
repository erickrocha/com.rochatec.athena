package com.rochatec.athena.security.user.box;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.client.service.SecurityClientService;
import com.rochatec.athena.humanresources.employee.dialog.EmployeeDialog;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.Status;
import com.rochatec.athena.model.User;
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

public class UserSearchBox extends AbstractWrapperSearchBox<User>{
	
	protected SecurityClientService securityClientService = ServiceFactory.getInstance().getSecurityClientService();
	protected Employee selected; 

	public UserSearchBox(Composite parent, Executable<User> executable) {
		super(parent, executable);
	}

	@Override
	public List<IColumn> getColumns() {
		List<IColumn> columns = new ArrayList<IColumn>();
		columns.add(new Column("id",Messages.getMessage("user.field.label.id"),ColumnType.LONG));
		columns.add(new Column("profile",Messages.getMessage("user.field.label.profile"),ColumnType.OBJECT));
		columns.add(new Column("username",Messages.getMessage("user.field.label.username"),ColumnType.STRING));
		columns.add(new Column("name",Messages.getMessage("user.field.label.name"),ColumnType.NAME));
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
		 		e.box.setEnabledCalendarCombo(false);
		 		e.box.setEnabledCombo(true);
		 		e.box.setEnabledText(true);
		 	break;
		 	case STRING:
		 		e.box.setEnabledCalendarCombo(false);
		 		e.box.setEnabledCombo(true);
		 		e.box.setEnabledText(true);
		 	break;
		 	case OBJECT:
		 		e.box.setEnabledCalendarCombo(false);
		 		e.box.setEnabledCombo(true);
		 		e.box.setEnabledText(true);
			break;
		default:
			break;
		}
		
	}

	@Override
	public List<User> fireSearchBoxActivated(SearchBoxEvent e) {
		List<User> users = new ArrayList<User>();
		switch (e.column.getSQLType()) {
		case LONG:
			users.clear();
			users.add(securityClientService.findUserById(e.searchBox.getLong()));
			break;
		case NAME:
			users = securityClientService.findUserByName(e.value);
			break;
		case OBJECT:
			if (selected != null){
				users = securityClientService.findUserByName(selected.getName());
			}else{
				users = securityClientService.findUserByName(e.value);
			}
		case STRING:
			users.clear();
			users.add(securityClientService.findUserByLogin(e.value));			
			break;
		default:
			break;
		}
		return users;
	}
	
	class CategoryDialogListener implements DialogListener{

		@Override
		public void showDialog(DialogEvent event) {
			switch (event.keyCode) {
			case IKeyPadConstants.KEY_F9:
				EmployeeDialog dialog = new EmployeeDialog(event.display.getActiveShell());
				selected = dialog.dialog();
				event.searchBox.setTextValue(selected != null ? selected.getName() : "");
				break;
			default :
				break;
			}
		}
	}

}
