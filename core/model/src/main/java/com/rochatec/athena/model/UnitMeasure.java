package com.rochatec.athena.model;

import java.util.ArrayList;
import java.util.List;

public enum UnitMeasure {

	UN,KG,LT;
	
	public static List<UnitMeasure> getAll(){
		List<UnitMeasure> list = new ArrayList<UnitMeasure>();
		for (UnitMeasure unitMeasure : UnitMeasure.values()){
			list.add(unitMeasure);
		}
		return list;
	}
}
