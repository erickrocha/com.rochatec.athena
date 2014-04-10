package com.rochatec.graphics.selection;

import com.rochatec.graphics.jface.ISingleSelection;


public class SingleSelection implements ISingleSelection{

	private Object object;
	
	/**
     * The canonical empty selection. This selection should be used instead of
     * <code>null</code>.
     */
    public static final SingleSelection EMPTY = new SingleSelection();
    
    public SingleSelection() {		
	}
	
	public SingleSelection(Object object) {
		this.object = object;
	}
	
	@Override
	public boolean isEmpty() {
		if (object != null)
			return true;
		return false;
	}

	@Override
	public Object getElement() {		
		return object;
	}

}
