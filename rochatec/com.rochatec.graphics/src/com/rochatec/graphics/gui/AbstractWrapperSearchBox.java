package com.rochatec.graphics.gui;

import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.event.ColumnChangedEvent;
import com.rochatec.graphics.event.SearchBoxEvent;
import com.rochatec.graphics.listener.ColumnListener;
import com.rochatec.graphics.listener.SearchBoxListener;
import com.rochatec.graphics.model.IColumn;

public abstract class AbstractWrapperSearchBox<T> {
	
	private Executable<T> executable;
	private SearchBox searchBox;
	

	public AbstractWrapperSearchBox(Composite parent, Executable<T> executable) {
		this.executable = executable;
		createContents(parent);
	}

	private void createContents(Composite parent) {
		searchBox = new SearchBox(parent, SWT.NONE);
		searchBox.setColumns(getColumns());
		searchBox.setStatusLabelProvider(getLabelProvider());
		searchBox.setInputStatus(getStatus());
		searchBox.setEnabledCalendarCombo(false);
		searchBox.setEnabledCombo(false);
		searchBox.addColumnChangedListener(new ColumnChangedListener());
		searchBox.addSearchListener(new SearchBoxActivedhListener());
	}
	
	public abstract  List<IColumn> getColumns();
	
	public abstract IBaseLabelProvider getLabelProvider();
	
	public abstract Object getStatus();
	
	public abstract void fireColumnsChanged(ColumnChangedEvent e);
	
	public abstract List<T> fireSearchBoxActivated(SearchBoxEvent e);
	
	public void setFocus(){
		searchBox.setFocus();
	}
	
	private void execute(List<T> rows){
		executable.execute(rows);
	}

	class ColumnChangedListener implements ColumnListener {

		@Override
		public void columnChanged(ColumnChangedEvent e) {
			fireColumnsChanged(e);
		}
	}

	class SearchBoxActivedhListener implements SearchBoxListener {
		@Override
		public void search(SearchBoxEvent e) {
			List<T> rows = fireSearchBoxActivated(e);			
			execute(rows);
		}
	}
}
