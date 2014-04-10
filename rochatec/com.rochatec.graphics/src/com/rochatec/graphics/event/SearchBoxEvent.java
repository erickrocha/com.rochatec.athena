package com.rochatec.graphics.event;

import java.util.Calendar;

import org.eclipse.swt.events.TypedEvent;

import com.rochatec.graphics.gui.SearchBox;
import com.rochatec.graphics.model.IColumn;

public class SearchBoxEvent extends TypedEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6048054590998551002L;
	
	public IColumn column;
	public SearchBox searchBox;
	public String value;
	public Object status;	
	public Calendar begin;
	public Calendar end;

	public SearchBoxEvent(TypedEvent e) {
		super(e);
	}

	
}
