package com.rochatec.pos.athena.context;

public interface IContext {
	
	public static final Long HOSTNAME = 0x00001L;
	public static final Long PORT = 0x00002L;
	public static final Long PROTOCOL = 0x00003L;
	public static final Long WEB_CONTEXT = 0x00004L;
	public static final Long BOX_STATE = 0x00005L;
	public static final Long BOX_USER = 0x00006L;
	
	public static final String STATUS_DAY = "STATUS_DAY";
	
	public Object getAttribute(Long key);
	
	public Long getLong(Long key);
	
	public Double getDouble(Long key);
	
	public Integer getInteger(Long key);
	
	public String getString(Long key);
	
	public Boolean getBoolean(Long key);
	
	public void setAttribute(Long key,Object value);
	
	public void setAttribute(Long key,Long value);
	
	public void setAttribute(Long key,Double value);
	
	public void setAttribute(Long key,Float value);
	
	public void setAttribute(Long key,Integer value);
	
	public void setAttribute(Long key,String value);
	
	public void setAttribute(Long key,Boolean value);
	
	public String getUrl();	
}
