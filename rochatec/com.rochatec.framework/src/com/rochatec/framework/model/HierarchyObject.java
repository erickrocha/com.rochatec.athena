package com.rochatec.framework.model;

import java.util.List;

import org.eclipse.swt.graphics.Image;

public class HierarchyObject {

	private HierarchyObject parent;
	private String label;
	private Object wrapper;
	private Image image;
	private List<HierarchyObject> childs;

	public HierarchyObject() {

	}

	public HierarchyObject(String label,HierarchyObject parent, Object wrapper,
			List<HierarchyObject> childs) {
		this.label = label;
		this.parent = parent;
		this.wrapper = wrapper;
		this.childs = childs;
	}

	public HierarchyObject getParent() {
		return parent;
	}

	public void setParent(HierarchyObject parent) {
		this.parent = parent;
	}

	public Object getWrapper() {
		return wrapper;
	}

	public void setWrapper(Object wrapper) {
		this.wrapper = wrapper;
	}

	public List<HierarchyObject> getChilds() {
		return childs;
	}

	public void setChilds(List<HierarchyObject> childs) {
		this.childs = childs;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return label;
	}

}
