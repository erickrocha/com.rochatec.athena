package com.rochatec.pos.athena.main;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.listeners.impl.ApplicationListenerImpl;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.utils.ServiceFactory;

/**
 * Created by epr on 01/07/15.
 */
public class Activator {

    public static void main(String[] args) {
        ServiceFactory factory = new ServiceFactory("spring.xml");
        ApplicationController controller = new ApplicationController(factory);
        controller.load();
        AthenaApplicationWindow athenaApplicationWindow = new AthenaApplicationWindow();
        athenaApplicationWindow.addApplicationListener(new ApplicationListenerImpl(controller));
        athenaApplicationWindow.run();
    }

}
