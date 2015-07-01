package com.rochatec.pos.athena.app;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.springframework.context.ApplicationContext;

import com.rochatec.pos.athena.persistence.model.Box;
import com.rochatec.pos.athena.persistence.model.BoxStatus;
import com.rochatec.pos.athena.persistence.model.Operator;
import com.rochatec.pos.athena.persistence.service.IExecuteService;
import com.rochatec.pos.athena.persistence.service.ISaleService;
import com.rochatec.pos.athena.persistence.service.ISecurityService;
import com.rochatec.pos.athena.persistence.service.impl.ExecuteServiceImpl;
import com.rochatec.pos.athena.persistence.service.impl.SalesServiceImpl;
import com.rochatec.pos.athena.persistence.service.impl.SecurityServiceImpl;
import com.rochatec.pos.athena.tools.POSATHENA;

public class ApplicationController {

	private ApplicationContext context;
	
	private Map<String,Object> session;
	
	private Box box;
	
	private IPreferenceStore preferenceStore;
	
	public ApplicationController(ApplicationContext context,IPreferenceStore preferenceStore) {
		this.context = context;
		this.preferenceStore = preferenceStore;
		session = new HashMap<String,Object>();
		init();
	}
	
	private void init(){
		BoxStatus boxStatus = null;
		if (preferenceStore.getString(POSATHENA.BOX_STATUS).equals("")){
			boxStatus = BoxStatus.CLOSED;
		}else{
			boxStatus = BoxStatus.valueOf(preferenceStore.getString(POSATHENA.BOX_STATUS));
		}		
		if (BoxStatus.OPEN.equals(boxStatus)){
			String operatorKey = preferenceStore.getString(POSATHENA.BOX_USER_ID);
			Operator operator = getSecurityService().findOperatorByKey(operatorKey);
			box = getSaleService().findBoxByOperatorAndOpen(operator);
			addParameter(POSATHENA.CURRENT_OPERATOR,operator);			
		}
		addParameter(POSATHENA.BOX_STATUS,boxStatus);
	}
	
	public ApplicationContext getSpringContext() {
		return context;
	}	
	
	public ISaleService getSaleService(){
		return (ISaleService)context.getBean(SalesServiceImpl.class);
	}
	
	public ISecurityService getSecurityService() {
		return (ISecurityService)context.getBean(SecurityServiceImpl.class);
	}
	
	public IExecuteService getExecutionService() {
		return (IExecuteService)context.getBean(ExecuteServiceImpl.class);
	}
	
	public void addParameter(String key,Object value){
		this.session.put(key, value);
	}
	
	public Object getParameter(String key){
		return this.session.get(key);
	}
	
	public Map<String,Object> getSession(){
		return session; 
	}

	public IPreferenceStore getPreferenceStore() {
		return preferenceStore;
	}
	
	public BoxStatus getStatus(){
		if (box != null){
			return box.getStatus();
		}
		return (BoxStatus)getParameter(POSATHENA.BOX_STATUS);
	}
	
}
