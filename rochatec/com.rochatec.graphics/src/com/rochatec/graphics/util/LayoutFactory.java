package com.rochatec.graphics.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class LayoutFactory {
	
	private static LayoutFactory instance;
	
	private LayoutFactory() {
	}
	
	public static LayoutFactory getInstance(){
		return instance != null ? instance : new LayoutFactory();
	}
	
	
	public GridLayout getGridLayout(int columns,boolean makeEqualsColumnsWidth){
		return getGridLayout(columns, makeEqualsColumnsWidth,0);
	}
	
	public GridLayout getGridLayout(int columns,boolean makeEqualsColumnsWidth,int margin){
		GridLayout layout = new GridLayout(columns,makeEqualsColumnsWidth);
		layout.marginBottom = margin;
		layout.marginLeft = margin;
		layout.marginRight = margin;
		layout.marginTop = margin;
		return layout;
	}
	
	public GridLayout getGridLayout(int columns,int margin){
		return getGridLayout(columns,false, margin);
	}
	
	
	public GridLayout getGridLayout(int columns){
		return getGridLayout(columns,false);
	}
	
	public GridLayout getGridLayout(){
		return getGridLayout(1,false);
	}
	
	public TableWrapLayout getTableWrapLayout(int columns,boolean makeEqualsColumnsWidth){
		return getTableWrapLayout(columns,makeEqualsColumnsWidth,0);
	}
	
	public TableWrapLayout getTableWrapLayout(int columns,boolean makeEqualsColumnsWidth,int margin){
		TableWrapLayout layout = new TableWrapLayout();
		layout.horizontalSpacing = margin;
		layout.verticalSpacing = margin;
		layout.bottomMargin = margin;
		layout.leftMargin = margin;
		layout.topMargin = margin;
		layout.rightMargin = margin;
		layout.numColumns = columns;
		layout.makeColumnsEqualWidth = makeEqualsColumnsWidth;
		return layout;
	}
	
	public TableWrapLayout getTableWrapLayout(int columns){
		return getTableWrapLayout(columns,false);
	}
	
	public TableWrapLayout getTableWrapLayout(){
		return getTableWrapLayout(1);
	}
	
	public FillLayout getFillLayout(){
		FillLayout fillLayout = new FillLayout();
		return fillLayout;
	}
	
	public RowLayout getRowLayout(){
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);		
		return layout;
	}
}
