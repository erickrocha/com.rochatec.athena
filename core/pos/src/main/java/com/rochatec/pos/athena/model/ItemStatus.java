package com.rochatec.pos.athena.model;

import java.util.ArrayList;
import java.util.List;

public enum ItemStatus {
	
	OPEN,CANCELED,FINISHED;
	
	
	public static List<Status>  getAll(){
		List<Status> allStatus = new ArrayList<Status>();
		for (Status status : Status.values()){
			allStatus.add(status);
		}
		return allStatus;
	}
}
