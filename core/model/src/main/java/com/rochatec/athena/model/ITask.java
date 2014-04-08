package com.rochatec.athena.model;

import java.util.Calendar;
import java.util.Date;

public interface ITask {

	public Long getId();
	
	public String getName();
	
	public Calendar getDateRegister();
	
	public Calendar getStartDate();
	
	public Date getStartTime();
	
	public Calendar getEndDate();
	
	public Date getEndTime();
	
	public Object getStatus();
}
