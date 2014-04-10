package com.rochatec.graphics.gui;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.ImageHyperlink;

import com.rochatec.graphics.Activator;
import com.rochatec.graphics.adapter.HyperLinkAdapter;
import com.rochatec.graphics.event.ColumnChangedEvent;
import com.rochatec.graphics.event.DialogEvent;
import com.rochatec.graphics.event.SearchBoxEvent;
import com.rochatec.graphics.listener.ColumnListener;
import com.rochatec.graphics.listener.DialogListener;
import com.rochatec.graphics.listener.SearchBoxListener;
import com.rochatec.graphics.model.IColumn;
import com.rochatec.graphics.provider.ColumnsLabelProvider;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.util.IResources;
import com.rochatec.graphics.util.LayoutFactory;
import com.rochatec.graphics.util.Message;
import com.rochatec.graphics.util.WidgetUtils;

public class SearchBox {

	protected DateChooserCombo beginCalendarCombo;
	protected DateChooserCombo endCalendarCombo;
	protected ComboViewer status;
	protected ComboViewer columns;
	protected Text txtValue;

	protected ImageHyperlink linkSearch;

	private Group groupDate;
	private Group groupStatus;
	private Group groupValues;

	private ListenerList listeners = new ListenerList();
	private ListenerList changedListeners = new ListenerList();
	private ListenerList dialogListeners = new ListenerList();

	protected IColumn columnSelected;
	protected Object statusSelected;

	public SearchBox(Composite parent, int style) {
		createContents(parent);
		WidgetUtils.backgroundEquals(parent);
	}
	
	public void clear(){
		txtValue.setText("");
		groupValues.setText("");
		beginCalendarCombo.setValue(null);
		endCalendarCombo.setValue(null);
	}

	private void createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(LayoutFactory.getInstance().getGridLayout(6, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Group groupColumns = new Group(composite, SWT.NONE);
		groupColumns.setText(Message.getMessage("search.box.columns.label"));
		groupColumns.setLayout(LayoutFactory.getInstance().getGridLayout(1, 1));
		groupColumns.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

		new Label(groupColumns, SWT.NONE);
		columns = new ComboViewer(new CCombo(groupColumns, SWT.DROP_DOWN| SWT.BORDER | SWT.READ_ONLY));
		columns.getCCombo().setLayoutData(new GridData(150, 22));
		columns.setContentProvider(new GenericContentProvider<IColumn>());
		columns.setLabelProvider(new ColumnsLabelProvider());
		columns.addSelectionChangedListener(new ChangeColumnListener());

		groupValues = new Group(composite, SWT.NONE);
		groupValues.setText(Message.getMessage("search.box.column.id"));
		groupValues.setLayout(LayoutFactory.getInstance().getGridLayout(1, 1));
		groupValues
				.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		new Label(groupValues, SWT.NONE);
		txtValue = new Text(groupValues, SWT.BORDER);
		txtValue.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		txtValue.addKeyListener(new DialogSelectedListener());

		groupDate = new Group(composite, SWT.NONE);
		groupDate.setText(Message.getMessage("search.box.group.period"));
		groupDate.setLayout(LayoutFactory.getInstance().getGridLayout(2, 1));
		groupDate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
		new Label(groupDate, SWT.NONE).setText(Message.getMessage("search.box.start.date"));
		new Label(groupDate, SWT.NONE).setText(Message.getMessage("search.box.end.date"));
		beginCalendarCombo = new DateChooserCombo(groupDate,SWT.BORDER);
		endCalendarCombo = new DateChooserCombo(groupDate,SWT.BORDER);

		groupStatus = new Group(composite, SWT.NONE);
		groupStatus.setText(Message.getMessage("search.box.status"));
		groupStatus.setLayout(LayoutFactory.getInstance().getGridLayout(1, 1));
		groupStatus.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

		new Label(groupStatus, SWT.NONE);
		status = new ComboViewer(new CCombo(groupStatus, SWT.DROP_DOWN|SWT.BORDER));
		status.setContentProvider(new GenericContentProvider<Status>());		
		status.addSelectionChangedListener(new StatusChangeListener());

		Group group = new Group(composite, SWT.NONE);
		group.setText("     ");
		group.setLayout(LayoutFactory.getInstance().getGridLayout(1, 1));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

		linkSearch = new ImageHyperlink(group, SWT.NONE);
		linkSearch.setImage(Activator.getImageDescriptor(IResources.ICON_SEARCH_32).createImage());
		linkSearch.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		linkSearch.addHyperlinkListener(new HyperLinkListener());
		postCreate();
	}
	
	public void setFocus(){
		txtValue.setFocus();
	}

	public void setColumns(List<IColumn> columnsInput) {
		columns.setInput(columnsInput);
		columns.getCCombo().select(0);
	}

	private void postCreate() {
		columns.getCCombo().select(0);
		beginCalendarCombo.setEnabled(false);
		endCalendarCombo.setEnabled(false);
		status.getCCombo().setEnabled(false);
		txtValue.setEnabled(true);
	}

	private void columnChanged(SelectionChangedEvent event) {
		ColumnChangedEvent e = new ColumnChangedEvent(event);
		e.column = columnSelected;
		e.box = this;
		for (Object listener : changedListeners.getListeners()) {
			((ColumnListener) listener).columnChanged(e);
		}
	}
	
	private void dialogSelected(KeyEvent event){		
		DialogEvent dialogEvent = new DialogEvent(event);
		dialogEvent.column =  columnSelected;
		dialogEvent.searchBox = this;
		dialogEvent.keyCode = event.keyCode;
		dialogEvent.character = event.character;
		dialogEvent.display = event.display;				
		for (Object listener : dialogListeners.getListeners()){
			((DialogListener)listener).showDialog(dialogEvent);
		}
	}
	
	public void addSearchListener(SearchBoxListener listener){
		listeners.add(listener);
	}
	
	public  void removeSearchListener(SearchBoxListener listener){
		listeners.remove(listener);
	}
	
	public  void addColumnChangedListener(ColumnListener listener){
		changedListeners.add(listener);
	}
	
	public  void removeColumnChangedListener(ColumnListener listener){
		changedListeners.remove(listener);
	}
	
	public  void addDialogListener(DialogListener listener){
		this.dialogListeners.add(listener);
	}
	
	public  void removeDialogListener(DialogListener listener){
		this.dialogListeners.remove(listener);
	}
	
	public void setVisiblePeriodBox(boolean visible){
		this.groupDate.setVisible(visible);
	}
	
	public void setVisibleStatusBox(boolean visible){
		this.groupStatus.setVisible(visible);
	}
	
	public void setLabelValue(String value){
		groupValues.setText(value);
	}
	
	public void setTextValue(String value){
		txtValue.setText(value);
	}
	
	public void setEnabledCalendarCombo(boolean value){
		beginCalendarCombo.setEnabled(value);
		endCalendarCombo.setEnabled(value);
	}
	
	public void setTextMessage(String message){
		this.txtValue.setMessage(message);
	}
	
	public void setEnabledText(boolean value){
		txtValue.setEnabled(value);
	}
	
	public void setEnabledCombo(boolean value){
		status.getCCombo().setEnabled(value);
	}
	
	public Calendar getStartDate(){
		if (beginCalendarCombo.getValue() != null){
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(beginCalendarCombo.getValue());
			calendar.set(Calendar.HOUR,0);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			calendar.set(Calendar.MILLISECOND,0);
			return calendar;
		}
		return null;
	}
	
	public Calendar getEndDate(){
		if (endCalendarCombo.getValue() != null){
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(endCalendarCombo.getValue());
			calendar.set(Calendar.HOUR,23);
			calendar.set(Calendar.MINUTE,59);
			calendar.set(Calendar.SECOND,59);
			calendar.set(Calendar.MILLISECOND,0);
			return calendar;
		}
		return null;
	}
	
	public String getText(){
		return txtValue.getText();
	}
	
	public Long getLong(){
		Long value = Long.parseLong(txtValue.getText());
		return value;
	}
	
	public Integer getInteger(){
		Integer value = Integer.parseInt(txtValue.getText());
		return value;
	}
	
	public void setStatusLabelProvider(IBaseLabelProvider labelProvider){
		status.setLabelProvider(labelProvider);
	}
	
	public void setStatusContentProvider(IContentProvider contentProvider){
		status.setContentProvider(contentProvider);
	}
	
	public void setInputStatus(Object input){
		status.setInput(input);
		status.getCCombo().select(0);
	}
	
	private void search(HyperlinkEvent event){
		
		SearchBoxEvent e = new SearchBoxEvent(event);
		e.column = columnSelected;
		e.status = statusSelected;
		e.value = txtValue.getText();
		e.begin = getStartDate();
		e.end = getEndDate();
		e.searchBox = this;
		
		for (Object listener : listeners.getListeners()){
			 ((SearchBoxListener)listener).search(e);
		}
	}
	
	class HyperLinkListener extends HyperLinkAdapter{
		@Override
		public void linkActivated(HyperlinkEvent event) {
			search(event);
		}
	}

	class ChangeColumnListener implements ISelectionChangedListener {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection structuredSelection = (IStructuredSelection) event.getSelection();
			columnSelected = (IColumn) structuredSelection.getFirstElement();
			columnChanged(event);
			txtValue.setFocus();
		}

	}
	
	class DialogSelectedListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			dialogSelected(e);
		}
	}
	
	class StatusChangeListener implements ISelectionChangedListener{
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			IStructuredSelection structuredSelection = (IStructuredSelection) event.getSelection();
			statusSelected =  structuredSelection.getFirstElement();
			linkSearch.setFocus();
		}
		
	}

}
