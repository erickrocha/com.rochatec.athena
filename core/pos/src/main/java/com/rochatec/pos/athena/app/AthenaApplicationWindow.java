package com.rochatec.pos.athena.app;

import com.rochatec.pos.athena.app.composite.PaymentComposite;
import com.rochatec.pos.athena.app.composite.SellComposite;
import com.rochatec.pos.athena.app.composite.WelcomeComposite;
import com.rochatec.pos.athena.app.listeners.ApplicationKeyListenerImpl;
import com.rochatec.pos.athena.app.window.AthenaWindow;
import com.rochatec.pos.athena.utils.Colors;
import com.rochatec.pos.athena.utils.Messages;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by epr on 01/07/15.
 */
public class AthenaApplicationWindow extends ApplicationWindow {

    private Map<String,Composite> compositeMap = new HashMap<>();

    public AthenaApplicationWindow() {
        super(null);
    }

    @Override
    protected void setShellStyle(int newShellStyle) {
        super.setShellStyle(SWT.SYSTEM_MODAL | SWT.CLOSE);
    }

    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setMinimized(false);
        shell.setMaximized(true);
        shell.setText(Messages.getMessage("application.title"));
    }

    @Override
    protected void handleShellCloseEvent() {
        MessageBox messageBox = new MessageBox(getShell(),SWT.APPLICATION_MODAL | SWT.YES | SWT.NO);
        messageBox.setText(Messages.getMessage("app.dialog.close.title"));
        messageBox.setMessage(Messages.getMessage("app.dialog.close.message"));
        if (messageBox.open() == SWT.YES){
            super.handleShellCloseEvent();
        }
    }

    @Override
    protected Control createContents(Composite parent) {
        StackLayout stackLayout = new StackLayout();
        parent.setLayout(stackLayout);
        compositeMap.put(WelcomeComposite.ID, new WelcomeComposite(parent, SWT.BORDER));
        compositeMap.put(SellComposite.ID,new SellComposite(parent, SWT.BORDER));
        compositeMap.put(PaymentComposite.ID,new PaymentComposite(parent, SWT.BORDER));
        stackLayout.topControl = compositeMap.get(WelcomeComposite.ID);
        getShell().addKeyListener(new ApplicationKeyListenerImpl(stackLayout, compositeMap));
        return parent;
    }

    @Override
    protected Point getInitialSize() {
        return new Point(1024,768);
    }

    public void run() {
        setBlockOnOpen(true);
        open();
        Display.getCurrent().dispose();
    }

}
