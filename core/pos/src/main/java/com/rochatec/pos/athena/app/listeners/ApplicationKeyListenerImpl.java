package com.rochatec.pos.athena.app.listeners;

import com.rochatec.pos.athena.app.dialogs.ExecuteDialog;
import com.rochatec.pos.athena.app.event.FunctionEvent;
import com.rochatec.pos.athena.app.service.GUIService;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TypedEvent;

/**
 * Created by epr on 02/07/15.
 */
public class ApplicationKeyListenerImpl extends KeyAdapter {

    private ListenerList listeners = new ListenerList();

    public ApplicationKeyListenerImpl( GUIService guiService) {
        addFunctionListener(guiService);
    }

    public void addFunctionListener(FunctionListener listener){
        this.listeners.add(listener);
    }

    public void removeFunctionListener(FunctionListener listener){
        this.listeners.remove(listener);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.keyCode) {
            case SWT.F1:
                break;
            case SWT.F2:
                    execute(e);
                break;
            case SWT.F3:
                break;
            case SWT.F4:
                break;
            case SWT.F5:
                break;
            case SWT.F6:
                break;
            case SWT.F7:
                break;
            case SWT.F8:
                break;
            case SWT.F9:
                break;
            case SWT.F10:
                FunctionEvent sellEvent = new FunctionEvent(e);
                sellEvent.function = 1000;
                sendEvent(sellEvent);
                break;
            case SWT.F11:
                FunctionEvent payEvent = new FunctionEvent(e);
                payEvent.function = 1001;
                sendEvent(payEvent);
                break;
            case SWT.F12:
                break;

        }
    }

    private void sendEvent(FunctionEvent event){
        for (Object current :listeners.getListeners()){
            ((FunctionListener)current).execute(event);
        }
    }

    private void execute(TypedEvent event){
        ExecuteDialog dialog = new ExecuteDialog(event.display.getActiveShell());
        FunctionEvent newEvent = new FunctionEvent(event);
        newEvent.widget = event.widget;
        newEvent.display =event.display;
        newEvent.function = dialog.open();
        if (newEvent.function != 1){
            sendEvent(newEvent);
        }
    }

}
