package com.rochatec.athena.app;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.log.LogListener;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.rochatec.athena.app"; //$NON-NLS-1$
	
	private static final Logger LOGGER = Logger.getLogger(Activator.class);

	// The shared instance
	private static Activator plugin;
	
	private ILogListener listener;
	
	private MessageConsole console;
	private MessageConsoleStream stream;
	
	/**
	 * The constructor
	 */
	public Activator() {
		console = new MessageConsole(Messages.getMessage("application.console"),null);
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{console});
		stream = console.newMessageStream();
	}


	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		listener = new LogListener();
		Platform.addLogListener(listener); 
	}

	public void stop(BundleContext context) throws Exception {
		Platform.removeLogListener(listener);
		listener =null;
		plugin = null;
		super.stop(context);
	}

	public static Activator getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	public IViewPart getView(String id){
		return getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(id);
	}
	
	public void println(String msg) {
		stream.println(msg);
	}

	public void addConsoleError(Exception e) {
		for (StackTraceElement ste : e.getStackTrace()) {
			stream.println(ste.getFileName() + ":" + ste.getClassName() + ":"
					+ ste.getMethodName() + ":" + ste.getLineNumber());
		}
		LOGGER.error(e.getMessage());
	}

	public void addConsoleMessage(String message) {
		stream.println(message);
		LOGGER.info(message);
	}
}
