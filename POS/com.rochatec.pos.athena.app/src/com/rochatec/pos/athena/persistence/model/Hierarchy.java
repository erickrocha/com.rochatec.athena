package com.rochatec.pos.athena.persistence.model;

import java.util.ArrayList;
import java.util.List;

public enum Hierarchy {

	OPERATOR,FISCAL,MANAGER;
	
	public static List<Hierarchy>  getAll(){
		List<Hierarchy> allHierarchy = new ArrayList<Hierarchy>();
		for (Hierarchy status : Hierarchy.values()){
			allHierarchy.add(status);
		}
		return allHierarchy;
	}
}
