package com.rochatec.athena.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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
