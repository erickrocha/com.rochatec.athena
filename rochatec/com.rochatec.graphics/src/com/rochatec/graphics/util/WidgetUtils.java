package com.rochatec.graphics.util;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class WidgetUtils {
	
	public static void backgroundEquals(Composite parent) {
		for (Control c : parent.getChildren()) {
			if (c instanceof Composite) {
				c.setBackground(parent.getBackground());
				backgroundEquals((Composite) c);
			}
			if (c instanceof Text){
				c.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						((Text)e.widget).setBackground(Colors.getColor(SWT.COLOR_INFO_FOREGROUND));
					}
					
					@Override
					public void focusLost(FocusEvent e) {
						((Text)e.widget).setBackground(Colors.getColor(SWT.COLOR_WHITE));
					}
				});
			}
			if (c instanceof CTabFolder){
				c.setBackground(parent.getBackground());
				backgroundEquals((Composite) c);
			}
			c.setBackground(parent.getBackground());
		}
	}
	
	

	public static void clearForm(Control[] controls) {
		for (Control c : controls) {
			if (c instanceof Text) {
				((Text) c).setText("");
			}
			if (c instanceof Composite) {
				clearForm(((Composite) c).getChildren());
			}
		}
	}
	
	public static void addKeyListener(Control[] controls, KeyAdapter listener) {
		for (Control c : controls) {
			if (c instanceof Text) {
				((Text) c).addKeyListener(listener);
			}			
		}
	}
	
	public static void enableFields(Control[] controls,boolean value) {
		for (Control c : controls) {
			if (c instanceof Text) {
				((Text) c).setEnabled(value);
			}
			if (c instanceof Button){
				((Button)c).setEnabled(value);
			}
			if (c instanceof Composite) {
				enableFields(((Composite) c).getChildren(),value);
			}
		}
	}
	
	/**
	 * M�todo recebe o componente visual que vc deseja que seja o pr�ximo focus
	 * ent�o adiciona um listener ao componente de origem e verifica se a tecla 
	 * presionada � o Enter
	 * @param next
	 * @return
	 */
	public static KeyAdapter setNextFocusOnEnter(final Control next){
		KeyAdapter adapter = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == 13 || e.keyCode == 16777296){
					next.setFocus();
				}
			}
		};
		return adapter;
	}
	
	public static void decorateLabel(Composite parent,Font font,Color foreground){
		for (Control control : parent.getChildren()){
			if (control instanceof CLabel){
				CLabel label = (CLabel)control;
				label.setFont(font);
				label.setForeground(foreground);
				label.setAlignment(SWT.CENTER);
				label.setMargins(2,2,2,2);
				label.setToolTipText(label.getText());
			}
			
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static Object getAdapter(Object sourceObject, Class adapterType) {
    	Assert.isNotNull(adapterType);
        if (sourceObject == null) {
            return null;
        }
        if (adapterType.isInstance(sourceObject)) {
            return sourceObject;
        }

        if (sourceObject instanceof IAdaptable) {
            IAdaptable adaptable = (IAdaptable) sourceObject;

            Object result = adaptable.getAdapter(adapterType);
            if (result != null) {
                Assert.isTrue(adapterType.isInstance(result));
                return result;
            }
        } 
        
        if (!(sourceObject instanceof PlatformObject)) {
            Object result = Platform.getAdapterManager().getAdapter(sourceObject, adapterType);
            if (result != null) {
                return result;
            }
        }

        return null;
    }
	
	
}
