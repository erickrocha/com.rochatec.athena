package com.rochatec.pos.athena.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class Colors {
	
	private static Display display;
	
	static{
		display = Display.getCurrent();
	}
	
	public static Color getColor(int id){
		return display.getSystemColor(id);
	}
	
	public static Color getColorWhite(){
		return display.getSystemColor(SWT.COLOR_WHITE);
	}
	
	public static Color getInfoBackGround(){
		return display.getSystemColor(SWT.COLOR_INFO_BACKGROUND);
	}
	
	public static Color getColorRed(){
		return display.getSystemColor(SWT.COLOR_RED);
	}
	
	public static Color getColorBlue(){
		return display.getSystemColor(SWT.COLOR_BLUE);
	}
	
	public static Color getColorGreen(){
		return display.getSystemColor(SWT.COLOR_GREEN);
	}
	
	public static Color getColorBlack(){
		return display.getSystemColor(SWT.COLOR_BLACK);
	}
	
	public static Color getColorGray(){
		return display.getSystemColor(SWT.COLOR_GRAY);
	}
	
}
