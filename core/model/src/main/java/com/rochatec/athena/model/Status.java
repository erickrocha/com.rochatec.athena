package com.rochatec.athena.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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
