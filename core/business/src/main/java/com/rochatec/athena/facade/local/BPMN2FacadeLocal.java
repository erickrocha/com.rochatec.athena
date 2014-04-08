package com.rochatec.athena.facade.local;

import javax.ejb.Local;

@Local
public interface BPMN2FacadeLocal {
	
	public void startProcessById(String id);

}
