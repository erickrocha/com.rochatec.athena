package com.rochatec.pos.athena.app.service;

import com.rochatec.pos.athena.app.window.FunctionWindow;
import com.rochatec.pos.athena.model.Function;
import org.eclipse.swt.events.TypedEvent;

/**
 * Created by epr on 03/07/15.
 */
public class GUIService {

    public void execute(Function function, TypedEvent event) {
        switch (function.getId()) {
            case 999:
                FunctionWindow window = new FunctionWindow(event.display.getActiveShell());
                window.open();
                break;
        }
    }
}
