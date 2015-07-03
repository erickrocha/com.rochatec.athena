package com.rochatec.pos.athena.main;

import com.rochatec.pos.athena.app.AthenaApplicationWindow;
import com.rochatec.pos.athena.controller.ApplicationController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by epr on 01/07/15.
 */
public class Activator {

    private static ApplicationController controller;


    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AthenaApplicationWindow athenaApplicationWindow = new AthenaApplicationWindow();
        controller = new ApplicationController(context, athenaApplicationWindow);
        athenaApplicationWindow.run();
    }

    public static ApplicationController getController(){
        return controller;
    }

}
