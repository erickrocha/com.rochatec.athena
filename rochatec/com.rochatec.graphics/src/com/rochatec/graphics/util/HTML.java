package com.rochatec.graphics.util;

import java.util.List;

public class HTML {

	private static HTML instance;
	
	private HTML() {
		
	}
	
	public static HTML getInstance(){
		return instance != null ? instance : new HTML(); 
	}
	
	public String getList(String[] lines){
		StringBuilder builder = new StringBuilder();
		builder.append("<form>");		
		for (String line : lines){
			builder.append("<li><b>");
			builder.append(line);
			builder.append("</b></li>");
		}
		builder.append("</form>");
		return builder.toString();
	}	
	
	public String getList(List<String> lines){
		return getList((String[])lines.toArray());
	}
	
	public String getList(String line){
		return getList(new String[]{line});
	}
	
	public String getHeader(String line){
		StringBuilder builder = new StringBuilder();
		builder.append("<form>");		
		builder.append("<b>");
		builder.append(line);
		builder.append("</b>");		
		builder.append("</form>");
		return builder.toString();
	}
	
}
