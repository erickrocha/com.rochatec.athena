package com.rochatec.graphics.event;

import java.util.EventObject;

import com.rochatec.graphics.gui.SearchBox;
import com.rochatec.graphics.model.IColumn;

public class ColumnChangedEvent extends EventObject{
	
	
	public IColumn column;
	public SearchBox box;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6736348897197209891L;
	
	public ColumnChangedEvent(Object source) {
		super(source);
	}

	
	

}
