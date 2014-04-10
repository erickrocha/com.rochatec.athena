package com.rochatec.athena.humanresources.job.box;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.client.service.HumanResourceClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Job;
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

public class JobSearchBox extends AbstractWrapperSearchBox<Job>{

	protected HumanResourceClientService humanResourceClientService = ServiceFactory.getInstance().getHumanResourceClientService();

	public JobSearchBox(Composite parent, Executable<Job> executable) {
		super(parent, executable);		
	}

	@Override
	public List<IColumn> getColumns() {
		List<IColumn> columns = new ArrayList<IColumn>();
		columns.add(new Column("id",Messages.getMessage("job.field.id.label"),ColumnType.LONG));
		columns.add(new Column("name",Messages.getMessage("job.field.name.label"),ColumnType.STRING));
		columns.add(new Column("all",Messages.getMessage("job.field.all.label"),ColumnType.NONE));
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
	public List<Job> fireSearchBoxActivated(SearchBoxEvent e) {
		List<Job> jobs = new ArrayList<Job>();
		switch (e.column.getSQLType()) {
		case LONG:
			jobs.clear();
			jobs.add(humanResourceClientService.findJobById(e.searchBox
					.getInteger()));
			break;
		case STRING:
			jobs = humanResourceClientService.findJobsByName(e.value);
			break;
		case NONE:
			jobs = humanResourceClientService.findAllJobs();
			break;
		default:
			break;
		}
		return jobs;
	}
}
