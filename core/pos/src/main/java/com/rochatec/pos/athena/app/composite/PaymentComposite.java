package com.rochatec.pos.athena.app.composite;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.event.AppEvent;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.utils.Colors;
import com.rochatec.pos.athena.utils.FontToolkit;
import com.rochatec.pos.athena.utils.WidgetUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by epr on 02/07/15.
 */
public class PaymentComposite extends Composite {

    public static final String ID = "com.rochatec.pos.athena.app.composite.PaymentComposite";
    private AthenaApplicationWindow window;

    public PaymentComposite(AthenaApplicationWindow window,Composite parent, int style) {
        super(parent, style);
        this.window = window;
        createContents();
    }

    public void createContents() {
        setLayout(new GridLayout(1, true));
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite body = new Composite(this,SWT.NONE);
        body.setLayout(new GridLayout(1, false));
        body.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        WidgetUtils.backgroundEquals(body);
        this.addKeyListener(new ShellKeyImpl());
    }

    @Override
    protected void checkSubclass() {
    }

    class ShellKeyImpl extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            AppEvent event = new AppEvent(e);
            event.display = e.display;
            event.shell = e.display.getActiveShell().getShell();
            event.widget = e.widget;
            event.character = e.character;
            event.keyCode = e.keyCode;
            event.window = window;
            window.fireApplicationExecuteEvent(event);
        }
    }
}
