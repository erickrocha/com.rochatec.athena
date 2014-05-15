package com.rochatec.athena.security.user.form;

import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.client.service.SecurityClientService;
import com.rochatec.athena.exceptions.UserLoginException;
import com.rochatec.athena.exceptions.UserNotLoggedException;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.User;
import com.rochatec.athena.session.Request;
import com.rochatec.athena.util.ATHENA;
import com.rochatec.athena.util.Enviroment;
import com.rochatec.athena.util.IPathIcons;
import com.rochatec.athena.utils.ServiceFactory;
import com.rochatec.graphics.util.WidgetUtils;

public class UserLogin extends ApplicationWindow{
	
	protected String title;
	protected FormToolkit toolkit;
	protected ScrolledForm form;
	protected Image image;
	protected Label lblUsername;
	protected Label lblPassword;
	protected Text txtUsername;
	protected Text txtPassword;
	protected ImageHyperlink ihlLogin;
	protected SecurityClientService service = ServiceFactory.getInstance().getSecurityClientService();
	
	public UserLogin(Shell owner) {
		super(owner);
		setShellStyle(SWT.CLOSE|SWT.MIN|SWT.APPLICATION_MODAL);
		this.title = Messages.getMessage("user.login.title.label");		
		setBlockOnOpen(true);
		addStatusLine();
	}
	
	public UserLogin(){
		this(Activator.getDefault().getWorkbench().getDisplay().getActiveShell());
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(500,350);
	}
	
	@Override
	protected Control createContents(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.setText(title);
		getShell().setText(title);
		toolkit.decorateFormHeading(form.getForm());
		form.getBody().setLayout(null);
		createLoginBox(form.getBody());
		return parent;
	}
	
	private void  createLoginBox(Composite parent){
		parent.setBackgroundImage(new Image(parent.getDisplay(),Enviroment.getAppPath()+Enviroment.getString("user.login.background")));
		lblUsername = new Label(parent,SWT.NONE);
		lblUsername.setText(Messages.getMessage("user.login.username.label"));
		lblUsername.setBounds(270,110,65,15);
		lblUsername.setBackground(parent.getBackground());
		
		txtUsername = new Text(parent,SWT.BORDER);
		txtUsername.setBounds(337,110,100,20);
		
		lblPassword = new Label(parent,SWT.NONE);
		lblPassword.setText(Messages.getMessage("user.login.password.label"));
		lblPassword.setBounds(270,130,65,15);
		lblPassword.setBackground(parent.getBackground());
		
		txtPassword = new Text(parent,SWT.BORDER|SWT.PASSWORD);
		txtPassword.setBounds(337,130,100,20);
		
		ihlLogin = new ImageHyperlink(parent,SWT.NONE);
		ihlLogin.setBounds(337,150,100,36);
		ihlLogin.setBackground(parent.getBackground());
		ihlLogin.setImage(Activator.getImageDescriptor(IPathIcons.LOGIN_32).createImage());
		ihlLogin.setText(Messages.getMessage("user.login.button.label"));
		ihlLogin.addHyperlinkListener(new LoginListener());
		txtUsername.addKeyListener(WidgetUtils.setNextFocusOnEnter(txtPassword));
		txtPassword.addKeyListener(WidgetUtils.setNextFocusOnEnter(ihlLogin));
	}
	
	@Override
	protected StatusLineManager createStatusLineManager() {		
		return new StatusLineManager();
	}
	
	@Override
	protected void configureShell(Shell shell) {
		shell.setLayout(new FillLayout());
		super.configureShell(shell);
	}
	
	class LoginListener extends HyperlinkAdapter{
		@Override
		public void linkActivated(HyperlinkEvent event) {
			User user = null;
			try {
				user = service.login(txtUsername.getText().trim().toUpperCase(),txtPassword.getText().trim());
				if (user != null){
					Request.getSession().setAttribute(ATHENA.CURRENT_USER,user);
				}
				setReturnCode(IDialogConstants.OK_ID);
				close();
			} catch (UserLoginException ex) {
				setStatus(ex.getMessage());
			} catch (UserNotLoggedException e) {
				setStatus(e.getMessage());
			}
		}
	}
	
	
}
