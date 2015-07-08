package com.rochatec.pos.athena.app;

import com.rochatec.pos.athena.app.composite.PaymentComposite;
import com.rochatec.pos.athena.app.composite.SellComposite;
import com.rochatec.pos.athena.app.composite.WelcomeComposite;
import com.rochatec.pos.athena.app.listeners.ApplicationKeyListenerImpl;
import com.rochatec.pos.athena.app.service.GUIService;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.main.Activator;
import com.rochatec.pos.athena.utils.Colors;
import com.rochatec.pos.athena.utils.Messages;
import com.rochatec.pos.athena.utils.WidgetUtils;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by epr on 01/07/15.
 */
public class AthenaApplicationWindow extends ApplicationWindow {

    private Composite composite;
    private Map<String,Composite> compositeMap = new HashMap<>();
    private StackLayout stackLayout;
    private ApplicationController controller;
    private GUIService guiService;

    public AthenaApplicationWindow(ApplicationController controller) {
        super(null);
        this.controller = controller;
        this.guiService = new GUIService(controller,this);
    }

    @Override
    protected void setShellStyle(int newShellStyle) {
        super.setShellStyle(SWT.SHELL_TRIM |SWT.MAX| SWT.CLOSE);
    }

    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
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
        composite = new Composite(parent,SWT.NONE);
        stackLayout = new StackLayout();
        composite.setLayout(stackLayout);
        composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));


        compositeMap.put(WelcomeComposite.ID, new WelcomeComposite(composite, SWT.BORDER, controller));
        compositeMap.put(SellComposite.ID,new SellComposite(composite, SWT.BORDER,controller));
        compositeMap.put(PaymentComposite.ID,new PaymentComposite(composite, SWT.BORDER,controller));
        stackLayout.topControl = compositeMap.get(WelcomeComposite.ID);
        getShell().addKeyListener(new ApplicationKeyListenerImpl(guiService));

        return parent;
    }

    public Composite getComposite(String ID){
        return compositeMap.get(ID);
    }

    public void updateScreen(String ID){
        stackLayout.topControl = compositeMap.get(ID);
        composite.layout(true);
    }

    public void setPriceConsult(){
        Composite composite = compositeMap.get(SellComposite.ID);
        composite.setBackground(Colors.getInfoBackGround());
        stackLayout.topControl =composite;
        composite.layout(true);
    }

    public void setSell(){
        Composite composite = compositeMap.get(SellComposite.ID);
        composite.setBackground(Colors.getColorWhite());
        stackLayout.topControl =composite;
        composite.layout(true);
    }

    @Override
    protected Point getInitialSize() {
        return new Point(1024, 768);
    }

    public void run() {
        setBlockOnOpen(true);
        open();
        Display.getCurrent().dispose();
    }

}
