package com.rochatec.pos.athena.app;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Activator extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "com.rochatec.pos.athena.app"; //$NON-NLS-1$

	private static Activator plugin;
	
	private ApplicationContext springContext;
	
	private ApplicationController controller;
	
	public Activator() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		springContext = new ClassPathXmlApplicationContext("spring.xml");
		controller = new ApplicationController(springContext,this.getPreferenceStore());
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		springContext = null;
		controller = null;
		super.stop(context);
	}


	public static Activator getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public ApplicationController getController() {
		return controller;
	}
	
}
