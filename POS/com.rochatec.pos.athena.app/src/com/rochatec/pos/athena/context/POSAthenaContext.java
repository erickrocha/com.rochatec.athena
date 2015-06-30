package com.rochatec.pos.athena.context;

import java.util.HashMap;
import java.util.Map;

public class POSAthenaContext implements IContext{
	
	private Map<Long,Object> context;
	
	public POSAthenaContext() {
		this.context = new HashMap<Long, Object>();
	}
	
	public void setAttribute(Long key,Object value){
		this.context.put(key, value);
	}
	
	public Object getAttribute(Long key){
		return this.context.get(key);
	}

	@Override
	public void setAttribute(Long key, Long value) {
		this.context.put(key, value);
	}

	@Override
	public void setAttribute(Long key, Double value) {
		this.context.put(key, value);
	}

	@Override
	public void setAttribute(Long key, Float value) {
		this.context.put(key, value);	
	}

	@Override
	public void setAttribute(Long key, Integer value) {
		this.context.put(key, value);
	}

	@Override
	public void setAttribute(Long key, String value) {
		this.context.put(key, value);
	}

	@Override
	public void setAttribute(Long key, Boolean value) {
		this.context.put(key, value);
	}

	@Override
	public String getUrl() {
		String protocol = getString(IContext.PROTOCOL);
		String hostname = getString(IContext.HOSTNAME);
		Integer port = getInteger(IContext.PORT);
		String webContext = getString(IContext.WEB_CONTEXT);
		return protocol+"://"+hostname+":"+port.toString()+"/"+webContext;
	}

	@Override
	public Long getLong(Long key) {		
		return (Long)getAttribute(key);
	}

	@Override
	public Double getDouble(Long key) {		
		return (Double)getAttribute(key);
	}

	@Override
	public Integer getInteger(Long key) {
		return (Integer)getAttribute(key);
	}

	@Override
	public String getString(Long key) {
		return getAttribute(key).toString();
	}

	@Override
	public Boolean getBoolean(Long key) {
		return (Boolean)getAttribute(key);
	}
	
	
	
}
