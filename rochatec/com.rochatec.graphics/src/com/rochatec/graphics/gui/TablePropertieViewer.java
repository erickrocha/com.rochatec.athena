package com.rochatec.graphics.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import com.rochatec.framework.model.Property;
import com.rochatec.graphics.provider.PropertyContentProvider;
import com.rochatec.graphics.provider.PropertyLabelProvider;

public class TablePropertieViewer {

	protected TableViewer viewer;	
	
	public TablePropertieViewer(Composite parent,Map<String,String> values) {				
		viewer = new TableViewer(parent,SWT.FLAT |SWT.V_SCROLL | SWT.H_SCROLL);
		createColumns(values);
		this.viewer.setContentProvider(new PropertyContentProvider());
		this.viewer.setLabelProvider(new PropertyLabelProvider());
		setInput(values);
	}
	
	public TablePropertieViewer(Composite parent){
		this(parent,new HashMap<String,String>());
	}
	
	public Table getTable(){
		return viewer.getTable();
	}	
	
	private void createColumns(Map<String,String> values){
		TableViewerColumn key  = new TableViewerColumn(viewer,SWT.LEFT);		
		key.getColumn().setWidth(120);
		key.getColumn().setResizable(false);
		key.getColumn().setMoveable(false);
		
		TableViewerColumn value  = new TableViewerColumn(viewer,SWT.LEFT);		
		value.getColumn().setWidth(200);
		value.getColumn().setResizable(false);
		value.getColumn().setMoveable(false);
		
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
	
	
}
