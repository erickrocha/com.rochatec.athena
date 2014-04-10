package com.rochatec.athena.util;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.UnitMeasure;

public class UnitMeasureTradutor {
	
	public static String getLabel(UnitMeasure unitMeasure){
		switch (unitMeasure) {
		case KG:
			return Messages.getMessage("app.unitMeasure."+UnitMeasure.KG.toString());
		case LT:
			return Messages.getMessage("app.unitMeasure."+UnitMeasure.LT.toString());
		case UN:
			return Messages.getMessage("app.unitMeasure."+UnitMeasure.UN.toString());
		default:
			return "";
		}
	}
}
