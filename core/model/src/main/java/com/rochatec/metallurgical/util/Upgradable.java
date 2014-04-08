package com.rochatec.metallurgical.util;

public interface Upgradable<T> {

	public boolean isDirty(T object);
	
}
