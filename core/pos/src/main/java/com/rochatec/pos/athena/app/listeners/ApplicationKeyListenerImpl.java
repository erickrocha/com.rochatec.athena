package com.rochatec.pos.athena.app.listeners;

import com.rochatec.pos.athena.app.event.AppEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

/**
 * Created by epr on 02/07/15.
 */
public class ApplicationKeyListenerImpl extends KeyAdapter {

    @Override
    public void keyReleased(KeyEvent e) {
        AppEvent event = new AppEvent(e);
        event.display = e.display;
        event.shell = e.display.getActiveShell().getShell();
        event.widget = e.widget;
        event.character = e.character;
        event.keyCode = e.keyCode;

    }
}
