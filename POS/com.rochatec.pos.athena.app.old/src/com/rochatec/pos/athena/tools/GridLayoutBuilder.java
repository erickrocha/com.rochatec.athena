package com.rochatec.pos.athena.tools;

import org.eclipse.swt.layout.GridLayout;

public class GridLayoutBuilder {

	private static GridLayoutBuilder instance;
	
	private GridLayoutBuilder() {
		
	}
	
	public static GridLayoutBuilder getInstance(){
		if (instance == null){
			instance = new GridLayoutBuilder();
		}
		return instance;
	}
	
	public GridLayout build(){
		return new GridLayout();
	}
	
	public GridLayout build(int marginTop,int marginBotton,int marginLeft,int marginRight,int columns,boolean makeEqualsCoulumns){
		GridLayout gridLayout = new GridLayout(columns,makeEqualsCoulumns);
		gridLayout.marginBottom = marginBotton;
		gridLayout.marginTop = marginTop;
		gridLayout.marginRight = marginRight;
		gridLayout.marginLeft = marginLeft;
		return gridLayout;
	}
	
	public GridLayout build(int marginTop,int marginBotton,int marginLeft,int marginRight,int columns){
		return build(marginTop, marginBotton, marginLeft, marginRight, columns,false);
	}
	
	public GridLayout build(int marginTop,int marginBotton,int marginLeft,int marginRight){
		return build(marginTop, marginBotton, marginLeft, marginRight,1);
	}
	
	public GridLayout build(int marginHeight,int marginWidth,int columns,boolean makeEqualsCoulumns){
		GridLayout gridLayout = new GridLayout(columns,makeEqualsCoulumns);
		gridLayout.marginWidth = marginWidth;
		gridLayout.marginHeight = marginHeight;		
		return gridLayout;
	}
	
	public GridLayout build(int marginHeight,int marginWidth,int columns){		
		return build(marginHeight,marginWidth,columns,false);
	}
	
	public GridLayout build(int marginHeight,int marginWidth){		
		return build(marginHeight,marginWidth,1,false);
	}
	
}
