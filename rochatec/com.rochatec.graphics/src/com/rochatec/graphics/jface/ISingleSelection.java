package com.rochatec.graphics.jface;

import org.eclipse.jface.viewers.ISelection;

public interface ISingleSelection extends ISelection{

	
	/**
     * Returns the first element in this selection, or <code>null</code>
     * if the selection is empty.
     *
     * @return an element, or <code>null</code> if none
     */
    public Object getElement();

}
