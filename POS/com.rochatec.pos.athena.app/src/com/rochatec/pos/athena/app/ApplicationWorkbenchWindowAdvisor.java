package com.rochatec.pos.athena.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.rochatec.pos.athena.i18n.Message;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(1024,768));
        configurer.setShowCoolBar(false);  
        configurer.setShellStyle(SWT.SHELL_TRIM);
        configurer.setShowStatusLine(true);
    }
    
    @Override
    public void postWindowCreate() {
    	IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
    	Shell shell = configurer.getWindow().getShell();
    	shell.setMaximized(true);
    	shell.setFullScreen(true);
    	super.postWindowCreate();
    }
    
    @Override
   	public boolean preWindowShellClose() {
   		MessageBox msg = new MessageBox(getWindowConfigurer().getWorkbenchConfigurer().getWorkbench().getActiveWorkbenchWindow().getShell(),SWT.ICON_QUESTION|SWT.YES|SWT.NO);
   		msg.setText(Message.getMessage("app.dialog.close.title"));
   		msg.setMessage(Message.getMessage("app.dialog.close.message"));
   		int resp = msg.open();
   		if (resp == SWT.YES){
   			return true;
   		}
   		return false;
   	}
        
}
