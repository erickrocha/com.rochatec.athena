package com.rochatec.graphics.util;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

public class FontToolkit {
	
	private static final String TAHOMA = "Tahoma";
	
	private static FontToolkit instance;
	private Display display;
	
	private FontToolkit() {
		display = Display.getCurrent();
	}
	
	public static FontToolkit getInstance(){
		return instance != null ? instance : new FontToolkit();
	}
	
	public Font getFont(String name){
		 return JFaceResources.getFont(name);
	}
	
	public Font getFontDefault(){
		return JFaceResources.getDefaultFont();
	}
	
	public Font getTahoma(int size,int style){
		FontData fontData = JFaceResources.getFontRegistry().getFontData(TAHOMA)[0]; 
		fontData.setHeight(size);
		fontData.setStyle(style);
		Font font = new Font(display,fontData);
		return font;
	}
	
	public Font getTahomaHeader(){
		return getTahoma(30,SWT.BOLD);
	}
	
	public Font getTahomaLabel(){
		return getTahoma(12,SWT.NONE);
	}
}
