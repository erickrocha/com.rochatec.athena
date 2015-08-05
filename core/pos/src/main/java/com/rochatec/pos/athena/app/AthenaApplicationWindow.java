package com.rochatec.pos.athena.app;

import com.rochatec.pos.athena.app.composite.FooterComposite;
import com.rochatec.pos.athena.app.composite.PaymentComposite;
import com.rochatec.pos.athena.app.composite.SellComposite;
import com.rochatec.pos.athena.app.composite.WelcomeComposite;
import com.rochatec.pos.athena.app.event.AppEvent;
import com.rochatec.pos.athena.app.listeners.ApplicationListener;
import com.rochatec.pos.athena.utils.Messages;
import com.rochatec.pos.athena.utils.WidgetUtils;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.*;
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

    private Composite composite;
    private Map<String,Composite> compositeMap = new HashMap<>();
    private Map<String,Control> controlMap = new HashMap<>();
    private StackLayout stackLayout;
    private ListenerList listeners = new ListenerList();

    public AthenaApplicationWindow() {
        super(null);
    }

    public void register(String key,Control value){
        this.controlMap.put(key, value);
    }

    public Control getControl(String key){
        return this.controlMap.get(key);
    }

    public CLabel getCLabel(String key){
        return (CLabel)this.controlMap.get(key);
    }

    public Text getText(String key){
        return (Text)this.controlMap.get(key);
    }

    public ScrolledComposite getScrolledComposite(String key){
        return (ScrolledComposite)this.controlMap.get(key);
    }

    public StyledText getStyledText(String key){
        return (StyledText)this.controlMap.get(key);
    }


    public void addApplicationListener(ApplicationListener listener){
        this.listeners.add(listener);
    }

    public void removeApplicationListener(ApplicationListener listener){
        this.listeners.remove(listener);
    }

    public void fireApplicationExecuteEvent(AppEvent event){
        for (Object listener :listeners.getListeners()){
            ((ApplicationListener)listener).execute(event);
        }
    }

    public void fireApplicationActivatedEvent(AppEvent event){
        for (Object listener :listeners.getListeners()){
            ((ApplicationListener)listener).activated(event);
        }
    }

    @Override
    protected void setShellStyle(int newShellStyle) {
        super.setShellStyle(SWT.SHELL_TRIM | SWT.MAX | SWT.CLOSE);
    }

    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText(Messages.getMessage("application.title"));
        shell.addShellListener(new ShellListenerImpl());
        shell.addKeyListener(new ShellKeyImpl());
        shell.addFocusListener(new NotFocusLostShellListener(shell));
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
        parent.setLayout(new GridLayout(1,false));
        composite = new Composite(parent,SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
        stackLayout = new StackLayout();
        stackLayout.marginHeight = 0;
        stackLayout.marginWidth = 0;
        composite.setLayout(stackLayout);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        compositeMap.put(WelcomeComposite.ID, new WelcomeComposite(this, composite, SWT.BORDER));
        compositeMap.put(SellComposite.ID,new SellComposite(this,composite, SWT.BORDER));
        compositeMap.put(PaymentComposite.ID,new PaymentComposite(this,composite, SWT.BORDER));
        stackLayout.topControl = compositeMap.get(WelcomeComposite.ID);
        new FooterComposite(this,parent);
        WidgetUtils.setAllKeyListener(parent.getChildren(),new ShellKeyImpl());
        return parent;
    }

    public Composite getComposite(String ID){
        return compositeMap.get(ID);
    }

    public void updateScreen(String ID){
        stackLayout.topControl = compositeMap.get(ID);
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

    class ShellKeyImpl extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            AppEvent event = new AppEvent(e);
            event.display = e.display;
            event.shell = e.display.getActiveShell().getShell();
            event.widget = e.widget;
            event.character = e.character;
            event.keyCode = e.keyCode;
            event.window = AthenaApplicationWindow.this;
            fireApplicationExecuteEvent(event);
        }
    }

    class  ShellListenerImpl extends ShellAdapter{
        @Override
        public void shellActivated(ShellEvent e) {
            AppEvent event = new AppEvent(e);
            event.display = e.display;
            event.shell = e.display.getActiveShell().getShell();
            event.widget = e.widget;
            event.window = AthenaApplicationWindow.this;
            fireApplicationActivatedEvent(event);
        }

        @Override
        public void shellIconified(ShellEvent shellEvent) {
            AppEvent event = new AppEvent(shellEvent);
            event.display = shellEvent.display;
            event.shell = shellEvent.display.getActiveShell().getShell();
            event.widget = shellEvent.widget;
            event.window = AthenaApplicationWindow.this;
            fireApplicationActivatedEvent(event);
        }
    }

    class NotFocusLostShellListener extends FocusAdapter {

        Shell shell;

        public NotFocusLostShellListener(Shell shell) {
            this.shell = shell;
        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            shell.forceFocus();
        }
    }
}
