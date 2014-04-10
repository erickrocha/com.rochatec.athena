package com.rochatec.athena.util;


import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Status;

public class StatusTradutor {

	public static String getLabel(Status status){
		switch (status) {
		case ALL:
			return Messages.getMessage("app.status."+Status.ALL.toString());
		case ACTIVE:
			return Messages.getMessage("app.status."+Status.ACTIVE.toString());
		case INACTIVE:
			return Messages.getMessage("app.status."+Status.INACTIVE.toString());
		default:
			return "";
		}
	}
}
