package com.rochatec.framework.util;

import java.io.Serializable;

public class TextFormat implements Serializable  {

	private static final long serialVersionUID = -1732256968891475389L;
	public static final String CEP = "##.###-###";
	public static final String CPF = "###.###.###-##";
	public static final String CNPJ = "##.###.###/####-##";
	public static final String FONE = "(##) ####-####";

	private String pattern;

	public TextFormat() {
	}

	public TextFormat(String pattern) {
		this.pattern = pattern;
	}

	public String mask(String value) {
		if (value != null && !value.equals("")){
			String temp = "";
			for (int i = 0; i < value.length(); i++) {
				temp = pattern.replaceFirst("#",value.substring(i, i + 1));
				pattern = temp;
			}
			return temp;
		}
		return "";
	}

	public String unMask(String text) {
		return text != null && !text.equals("") ? text.replaceAll("[^0-9]", "") : "";
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
