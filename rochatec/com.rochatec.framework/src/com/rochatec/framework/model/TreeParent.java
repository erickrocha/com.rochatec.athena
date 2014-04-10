package com.rochatec.framework.model;

import java.util.ArrayList;
import java.util.List;

public class TreeParent extends TreeObject {

	private List<TreeObject> children;

	public TreeParent(String name,int type) {
		super(name,type);
		children = new ArrayList<TreeObject>();
	}

	public void addChild(TreeObject child) {
		children.add(child);
		child.setParent(this);
	}

	public void removeChild(TreeObject child) {
		children.remove(child);
		child.setParent(null);
	}

	public TreeObject[] getChildren() {
		return (TreeObject[]) children.toArray(new TreeObject[children.size()]);
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}
	
	public void clearChilds(){
		children = new ArrayList<TreeObject>();
	}
}
