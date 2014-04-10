package com.rochatec.athena.humanresources.employee.box;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.client.service.HumanResourceClientService;
import com.rochatec.athena.humanresources.job.dialog.JobDialog;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.model.Job;
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

public class EmployeeSearchBox extends AbstractWrapperSearchBox<Employee>{
	
	protected HumanResourceClientService humanResourceClientService = ServiceFactory.getInstance().getHumanResourceClientService();
	private Job current;
	
	public EmployeeSearchBox(Composite parent,Executable<Employee> executable) {
		super(parent, executable);
	}
	
	class JobDialogListener implements DialogListener {

		@Override
		public void showDialog(DialogEvent event) {
			switch (event.keyCode) {
			case IKeyPadConstants.KEY_F9:
				 JobDialog dialog = new JobDialog(event.display.getActiveShell());
				 current = dialog.dialog();
				 event.searchBox.setTextValue(current != null ? current.getName() : "");
				break;
			default:
				break;
			}

		}
	}

	@Override
	public List<IColumn> getColumns() {
		List<IColumn> columns = new ArrayList<IColumn>();
		columns.add(new Column("id",Messages.getMessage("employee.field.label.id"),ColumnType.LONG));
		columns.add(new Column("name",Messages.getMessage("employee.field.label.name"),ColumnType.NAME));
		columns.add(new Column("job",Messages.getMessage("employee.field.label.job"),ColumnType.OBJECT));
		columns.add(new Column("hiredate",Messages.getMessage("employee.field.label.hiredate"),ColumnType.DATE));
		columns.add(new Column("socialSecurity",Messages.getMessage("employee.field.label.socialsecurity"),ColumnType.STRING));		
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
		case OBJECT:
			e.box.setEnabledCalendarCombo(true);
			e.box.setEnabledCombo(true);
			e.box.setEnabledText(true);
			break;
		default:
			e.box.setEnabledText(true);
			break;
		}
		
	}

	@Override
	public List<Employee> fireSearchBoxActivated(SearchBoxEvent e) {
		Status status = (Status)e.status != null ? (Status)e.status : Status.ALL;
		List<Employee> employees = new ArrayList<Employee>();
		switch (e.column.getSQLType()) {
		case LONG:
			employees.clear();
			employees.add(humanResourceClientService
					.findEmployeeById(e.searchBox.getLong()));
			break;
		case NAME:
			employees = humanResourceClientService.findEmployeeByName(
					e.value, e.begin, e.end, status);
			break;
		case OBJECT:
			if (current != null)
				employees = humanResourceClientService.findEmployeeByJob(current,e.begin,e.end, status);
			else
				employees = humanResourceClientService.findEmployeeByJob(e.value,e.begin,e.end, status);
			break;
		case STRING:
			employees.clear();
			employees.add(humanResourceClientService
					.findEmployeeBySocialSecurity(e.value));
			break;
		case DATE:
			employees = humanResourceClientService.findEmployeeByHireDate(
					e.begin, e.end, status);
			break;
		default:
			break;
		}
		return employees;
	}
	
}
