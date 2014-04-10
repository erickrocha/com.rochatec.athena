package com.rochatec.framework.model;

import java.util.List;

public interface Executable<T> {
	
	public void execute(List<T> objects);
	
	public void execute(T object);
}
