package com.rochatec.athena.security.profile.box;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.client.service.SecurityClientService;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Profile;
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

public class ProfileSearchBox extends AbstractWrapperSearchBox<Profile>{
	
	private SecurityClientService securityClientService = ServiceFactory.getInstance().getSecurityClientService();

	public ProfileSearchBox(Composite parent, Executable<Profile> executable) {
		super(parent, executable);
	}

	@Override
	public List<IColumn> getColumns() {
		List<IColumn> columns = new ArrayList<IColumn>();
		columns.add(new Column("id",Messages.getMessage("profile.field.label.id"),ColumnType.LONG));
		columns.add(new Column("name",Messages.getMessage("profile.field.label.name"),ColumnType.NAME));
		columns.add(new Column("all",Messages.getMessage("profile.field.label.all"),ColumnType.NONE));
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
			e.box.setEnabledCombo(false);
			e.box.setEnabledText(true);
			break;
		case NONE:
			e.box.setEnabledCalendarCombo(false);
			e.box.setEnabledCombo(false);
			e.box.setEnabledText(false);
			break;
		default:
			break;
		}
		
	}

	@Override
	public List<Profile> fireSearchBoxActivated(SearchBoxEvent e) {
		List<Profile> profiles = new ArrayList<Profile>();
		switch (e.column.getSQLType()) {
		case LONG:
			profiles.clear();
			profiles.add(securityClientService.findProfileById(e.searchBox.getLong()));
			break;
		case NAME:
			profiles = securityClientService.findByProfileName(e.value);
			break;
		case NONE:
			profiles = securityClientService.findAllProfiles();
			break;
		default:
			break;
		}
		return profiles;
	}

}
