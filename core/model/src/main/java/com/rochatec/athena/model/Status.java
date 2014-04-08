package com.rochatec.athena.model;

import java.util.ArrayList;
import java.util.List;

public enum Status {
	ALL,ACTIVE,INACTIVE;
	
	
	public static List<Status>  getAll(){
		List<Status> allStatus = new ArrayList<Status>();
		for (Status status : Status.values()){
			allStatus.add(status);
		}
		return allStatus;
	}
}
