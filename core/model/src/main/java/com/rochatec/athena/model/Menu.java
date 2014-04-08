package com.rochatec.athena.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Menu implements Comparator<Menu>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5436959387001824659L;

	private Integer index;

	private String name;

	private String label;

	private Menu parent;

	private List<SubMenu> subMenus;

	public Menu() {
		
	}

	public Menu(Integer index, String name, String label) {
		super();
		this.index = index;
		this.name = name;
		this.label = label;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public List<SubMenu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<SubMenu> subMenus) {
		this.subMenus = subMenus;
	}
	
	public void addSubMenu(SubMenu subMenu){
		if (this.subMenus == null){
			this.subMenus = new ArrayList<SubMenu>();
		}
		this.subMenus.add(subMenu);
	}
	
	public void remove(SubMenu subMenu){
		if (this.subMenus != null){
			this.subMenus.remove(subMenu);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name+" "+label;
	}

	@Override
	public int compare(Menu o1, Menu o2) {
		return o1.getIndex().compareTo(o2.getIndex());
	}

}
