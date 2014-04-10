package com.rochatec.graphics.model;


public interface IColumn {

	public String getName();
	
	public String getLabel();
	
	public ColumnType getSQLType();
	
	@SuppressWarnings("rawtypes")
	public Class getClassName();
	
	
}
