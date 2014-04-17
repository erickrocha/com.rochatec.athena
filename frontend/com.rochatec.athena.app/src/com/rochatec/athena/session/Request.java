package com.rochatec.athena.session;

public class Request {

	private static Session session;

	static {
		session = new Session();
	}

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		Request.session = session;
	}

}
