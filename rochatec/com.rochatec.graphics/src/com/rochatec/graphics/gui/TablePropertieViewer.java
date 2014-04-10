package com.rochatec.graphics.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.rochatec.framework.model.Property;
import com.rochatec.graphics.provider.PropertyContentProvider;
import com.rochatec.graphics.provider.PropertyLabelProvider;

public class TablePropertieViewer {

	protected TableViewer viewer;
	protected Composite parent;
	
	public TablePropertieViewer(Composite parent,Map<String,String> values) {
		this.parent = parent;
		viewer = new TableViewer(parent,SWT.FLAT |SWT.V_SCROLL | SWT.H_SCROLL);
		createColumns(values);
		this.viewer.setContentProvider(new PropertyContentProvider());
		this.viewer.setLabelProvider(new PropertyLabelProvider());
		setInput(values);
		parent.addControlListener(new ControlListener());
	}
	
	public Display getDisplay(){
		return parent.getDisplay();
	}
	
	public Shell getActiveShell(){
		return parent.getShell(); 			
	}
	
	public void setLayoutDate(Object layoutData){
		this.viewer.getTable().setLayoutData(layoutData);		
	}
	
	public TablePropertieViewer(Composite parent){
		this(parent,new HashMap<String,String>());
		
	}
	
	public void addSelectionChangedListener(ISelectionChangedListener listener){
		this.viewer.addSelectionChangedListener(listener);
	}
	
	public void removeSelectionChangedListener(ISelectionChangedListener listener){
		this.viewer.removeSelectionChangedListener(listener);
	}
	
	public Table getTable(){
		return viewer.getTable();
	}	
	
	private void createColumns(Map<String,String> values){
		TableViewerColumn key  = new TableViewerColumn(viewer,SWT.LEFT);		
		key.getColumn().setWidth(getColumnSize(30));
		key.getColumn().setResizable(false);
		key.getColumn().setMoveable(false);
		
		TableViewerColumn value  = new TableViewerColumn(viewer,SWT.LEFT);		
		value.getColumn().setWidth(getColumnSize(70));
		value.getColumn().setResizable(false);
		value.getColumn().setMoveable(false);
	}
	
	public int getColumnSize(int percentage){
		int width = viewer.getTable().getClientArea().width;
		return width / 100 * percentage;
	}
		
	public void setInput(Map<String,String> values){
		List<Property> properties = new ArrayList<Property>();
		int count = 0;
		for (String key : values.keySet()){
			properties.add(new Property(key, values.get(key), count));
			count++;
		}		
		this.viewer.setInput(properties);
	}
	
	public void setInput(List<Property> properties){
		this.viewer.setInput(properties);
	}	 
	
	class ControlListener extends ControlAdapter{
		@Override
		public void controlResized(ControlEvent e) {
			Composite comp = (Composite)e.widget;
			Rectangle area = comp.getClientArea();
			Point size = viewer.getTable().computeSize(SWT.DEFAULT, SWT.DEFAULT);
			ScrollBar vBar = viewer.getTable().getVerticalBar();
			int width = area.width - viewer.getTable().computeTrim(0,0,0,0).width - vBar.getSize().x;
			if (size.y > area.height + viewer.getTable().getHeaderHeight()) {
				// Subtract the scrollbar width from the total column width
				// if a vertical scrollbar will be required
				Point vBarSize = vBar.getSize();
				width -= vBarSize.x;
			}
			Point oldSize = viewer.getTable().getSize();
			if (oldSize.x > area.width) {
				// table is getting smaller so make the columns 
				// smaller first and then resize the table to
				// match the client area width
				viewer.getTable().getColumns()[0].setWidth(width/3);
				viewer.getTable().getColumns()[1].setWidth(width - viewer.getTable().getColumns()[0].getWidth());
				viewer.getTable().setSize(area.width, area.height);
			} else {
				// table is getting bigger so make the table 
				// bigger first and then make the columns wider
				// to match the client area width
				viewer.getTable().setSize(area.width, area.height);
				viewer.getTable().getColumns()[0].setWidth(width/3);
				viewer.getTable().getColumns()[1].setWidth(width - viewer.getTable().getColumns()[0].getWidth());
			}
		}
	}
}
