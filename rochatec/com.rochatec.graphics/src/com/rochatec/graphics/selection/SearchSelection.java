package com.rochatec.graphics.selection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;

public class SearchSelection<T> implements IStructuredSelection{
	
	List<T> rows;
	
	public SearchSelection(T object) {
		rows = new ArrayList<T>();
		rows.add(object);
	}
	
	public SearchSelection() {
		this(new ArrayList<T>());
	}
	
	public SearchSelection(List<T> rows) {
		this.rows = rows;
	}
	
	@Override
	public boolean isEmpty() {
		return rows.isEmpty();
	}

	@Override
	public Object getFirstElement() {
		return rows.get(0);
	}

	@Override
	public Iterator<T> iterator() {
		return rows.iterator();
	}

	@Override
	public int size() {
		return rows.size();
	}

	@Override
	public Object[] toArray() {
		return rows.toArray();
	}

	@Override
	public List<T> toList() {
		return rows;
	}
	
	@SuppressWarnings("unchecked")
	public T getSingleSelection(){
		return (T)getFirstElement();
	}
}
