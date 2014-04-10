package com.rochatec.graphics.event;

import org.eclipse.swt.events.TypedEvent;

import com.rochatec.graphics.gui.SearchBox;
import com.rochatec.graphics.model.IColumn;

public class DialogEvent extends TypedEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3837738749960799064L;
	
	public IColumn column;

	public SearchBox searchBox;

	public int keyCode;

	public char character;
	
	public DialogEvent(TypedEvent e) {
		super(e);		
	}
	
	

}
