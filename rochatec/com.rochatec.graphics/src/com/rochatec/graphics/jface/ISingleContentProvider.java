package com.rochatec.graphics.jface;

import org.eclipse.jface.viewers.IContentProvider;

public interface ISingleContentProvider extends IContentProvider{
	
	
	/**
     * Returns the element to display in the viewer 
     * when its input is set to the given element. 
     * These elements can be presented as rows in a table, items in a list, etc.
     * The result is not modified by the viewer.
     * 
     * @param inputElement the input element
     * @return the object to display in the viewer
     */
    public Object getElement(Object inputElement);
}
