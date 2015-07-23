package com.rochatec.pos.athena.model;

import java.util.ArrayList;
import java.util.List;

public enum StatusSale {
	ACTIVE,CANCELED;
	
	public static List<StatusSale>  getAll(){
		List<StatusSale> allStatus = new ArrayList<StatusSale>();
		for (StatusSale status : StatusSale.values()){
			allStatus.add(status);
		}
		return allStatus;
	}
}
