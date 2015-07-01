package com.rochatec.pos.athena.tools;

public interface POSATHENA {
	
	public long CURRENT_USER = 0x123123;
	public long PATTERN_ENUM = 0x123122;
	public long PATTERN_SOCIALSECURITY = 0x123124;
	public long PATTERN_BIGDECIMAL = 0x123125;
	public long PATTERN_DATE = 0x123126;
	public long PATTERN_ZIPCODE = 0x123127;
	public long PATTERN_PHONE = 0x123128;
	public long PATTERN_NONE = 0x123129;
	public long PATTERN_WEIGHT = 0x123130;
		
	public String BOX_STATUS = "box.status";
	public String BOX_USER_ID = "box.user.id";
	public String CURRENT_OPERATOR = "current.operator";
	
	public String SERVER_PROTOCOL = "server.protocol";
	public String SERVER_HOSTNAME = "server.hostname";
	public String SERVER_PORT = "server.port";
	public String SERVER_CONTEXT = "server.context";
	
}
