package com.rochatec.graphics.test;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.rochatec.graphics.gui.CalendarField;
import com.rochatec.graphics.util.LayoutFactory;

public class CalendarFieldTest extends ApplicationWindow{

	public CalendarFieldTest() {
		super(null);
	}
	
	@Override
	protected Point getInitialSize() {		
		return new Point(800,600);
	}
	
	@Override
	protected Control createContents(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		ScrolledForm form = toolkit.createScrolledForm(parent);
		form.setText("Componentes Personalizados");
		toolkit.decorateFormHeading(form.getForm());
		form.getBody().setLayout(LayoutFactory.getInstance().getGridLayout(5,0));
		CalendarField field = new CalendarField(form.getBody());
		DateChooserCombo dateChooserCombo = new DateChooserCombo(form.getBody(),SWT.BORDER);
		return parent;
	}
	
	public void run() {
	    // Don't return from open() until window closes
	    setBlockOnOpen(true);

	    // Open the main window
	    open();

	    // Dispose the display
	    Display.getCurrent().dispose();
	  }

	public static void main(String[] args){
		new CalendarFieldTest().run();
	}
}
