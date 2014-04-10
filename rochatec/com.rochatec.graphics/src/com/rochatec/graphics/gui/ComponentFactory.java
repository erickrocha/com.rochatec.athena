package com.rochatec.graphics.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.rochatec.graphics.util.Colors;

public class ComponentFactory {
	
	private static ComponentFactory instance;
	
	private ComponentFactory() {
	}
	
	public static ComponentFactory getInstance(){
		return instance != null ? instance : new ComponentFactory();
	}
	
	public Text createText(Composite parent,int style){
		Text text = new Text(parent, style);
		text.addFocusListener(new FocusListenerImpl());
		return text;
	}
	
	public Text createText(Composite parent){
		return createText(parent,SWT.BORDER);
	}
	
	public Text createReadOnlyText(Composite parent,int style){
		Text text = new Text(parent, style|SWT.READ_ONLY);
		text.setEnabled(false);
		return text;
	}
	
	public Text createReadOnlyText(Composite parent){
		return createReadOnlyText(parent,SWT.BORDER);
	}
	
	
	class FocusListenerImpl extends FocusAdapter{
		@Override
		public void focusGained(FocusEvent e) {
			((Text)e.widget).setBackground(Colors.getColor(SWT.COLOR_INFO_BACKGROUND));
			((Text)e.widget).setText("");
		}
		@Override
		public void focusLost(FocusEvent e) {
			((Text)e.widget).setBackground(Colors.getColor(SWT.COLOR_WHITE));
		}
	}

}
