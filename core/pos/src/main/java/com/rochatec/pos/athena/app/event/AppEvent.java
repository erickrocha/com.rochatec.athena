package com.rochatec.pos.athena.app.event;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

import java.util.EventObject;

/**
 * Created by epr on 16/07/15.
 */
public class AppEvent extends EventObject {

    public Display display;
    public Shell shell;
    public Widget widget;
    public Integer keyCode;
    public Character character;
    public AthenaApplicationWindow window;

    public AppEvent(Object source) {
        super(source);
    }
}
