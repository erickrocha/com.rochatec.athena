package com.rochatec.pos.athena.app.service;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.event.FunctionEvent;
import com.rochatec.pos.athena.app.listeners.FunctionListener;
import com.rochatec.pos.athena.app.window.FunctionWindow;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.model.Function;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by epr on 03/07/15.
 */
public class GUIService implements FunctionListener {

    private static Logger LOGGER = Logger.getLogger(GUIService.class);


    private ApplicationController controller;
    private AthenaApplicationWindow athenaApplicationWindow;

    public GUIService(ApplicationController controller, AthenaApplicationWindow athenaApplicationWindow) {
        this.controller = controller;
        this.athenaApplicationWindow = athenaApplicationWindow;
    }

    @Override
    public void execute(FunctionEvent event) {

    }

    private void showFunctions(List<Function> functions, FunctionEvent event) {
        FunctionWindow window = new FunctionWindow(event.display.getActiveShell());
        window.setUpdate(functions);
        window.open();
    }
}
