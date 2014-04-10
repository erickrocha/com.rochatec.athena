package com.rochatec.framework.model;

public class TreeObject {

	private String name;
	private TreeParent parent;
	private Object object;
	private int type;
	
	public TreeObject(String name,int type,Object object) {
		this.name = name;
		this.type = type;
		this.object = object;
	}

	public TreeObject(String name,int type) {
		this(name, type,null);		
	}

	public String getName() {
		return name;
	}

	public void setParent(TreeParent parent) {
		this.parent = parent;
	}

	public TreeParent getParent() {
		return parent;
	}

	public String toString() {
		return getName();
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
