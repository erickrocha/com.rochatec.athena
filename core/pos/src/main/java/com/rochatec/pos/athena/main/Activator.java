package com.rochatec.pos.athena.main;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.controller.ApplicationController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by epr on 01/07/15.
 */
public class Activator {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ApplicationController controller = new ApplicationController(context);
        controller.load();
        AthenaApplicationWindow athenaApplicationWindow = new AthenaApplicationWindow(controller);
        athenaApplicationWindow.run();
    }

}
