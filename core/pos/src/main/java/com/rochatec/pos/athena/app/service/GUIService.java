package com.rochatec.pos.athena.app.service;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.app.composite.PaymentComposite;
import com.rochatec.pos.athena.app.composite.SellComposite;
import com.rochatec.pos.athena.app.composite.WelcomeComposite;
import com.rochatec.pos.athena.app.dialogs.LoginDialog;
import com.rochatec.pos.athena.app.event.FunctionEvent;
import com.rochatec.pos.athena.app.listeners.FunctionListener;
import com.rochatec.pos.athena.app.window.FunctionWindow;
import com.rochatec.pos.athena.controller.ApplicationController;
import com.rochatec.pos.athena.model.Function;
import com.rochatec.pos.athena.model.Operator;
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
        switch (event.function) {
            case 1000:
                if (controller.isOpen()) {
                    athenaApplicationWindow.updateScreen(SellComposite.ID);
                }
                break;
            case 1001:
                if (controller.isOpen()) {
                    athenaApplicationWindow.updateScreen(PaymentComposite.ID);
                }
                break;
            case 999:
                showFunctions(controller.getResourceService().findAllFunctions(), event);
                break;
            case 100:
                break;
            case 101:
                controller.executeOpenDay();
                ((WelcomeComposite)athenaApplicationWindow.getComposite(WelcomeComposite.ID)).fill();
                break;
            case 102:
                LoginDialog loginDialog = new LoginDialog(event.display.getActiveShell(), controller);
                Operator authorizedBy = loginDialog.show();
                Operator operator = loginDialog.show();
                controller.executeEnterOperator(operator.getKey());
                controller.getExecuteService().openBox(operator, authorizedBy, "0000001", "001", "100000");
                ((WelcomeComposite)athenaApplicationWindow.getComposite(WelcomeComposite.ID)).fill();
                break;
        }

    }

    private void showFunctions(List<Function> functions, FunctionEvent event) {
        FunctionWindow window = new FunctionWindow(event.display.getActiveShell());
        window.setUpdate(functions);
        window.open();
    }
}
