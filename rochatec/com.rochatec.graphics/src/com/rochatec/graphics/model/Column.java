package com.rochatec.graphics.model;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public class Column implements IColumn,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5240438960357052115L;
	
	private String name;
	private String label;
	private ColumnType type;
	private Class clazz;
	
	public Column() {
		
	}

	public Column(String name, String label, ColumnType type, Class clazz) {
		super();
		this.name = name;
		this.label = label;
		this.type = type;
		this.clazz = clazz;
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

	public void setType(ColumnType type) {
		this.type = type;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	@Override
	public ColumnType getSQLType() {
		return type;
	}
	
	@Override
	public Class getClassName() {
		return clazz;
	}

}
