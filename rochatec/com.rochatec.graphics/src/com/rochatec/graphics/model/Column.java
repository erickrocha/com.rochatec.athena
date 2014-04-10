package com.rochatec.graphics.model;

import java.io.Serializable;

public class Column implements IColumn,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5240438960357052115L;
	
	private String name;
	private String label;
	private ColumnType type;
	
	public Column() {
		
	}

	public Column(String name, String label, ColumnType type) {
		super();
		this.name = name;
		this.label = label;
		this.type = type;
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

	@Override
	public ColumnType getSQLType() {
		return type;
	}

}
