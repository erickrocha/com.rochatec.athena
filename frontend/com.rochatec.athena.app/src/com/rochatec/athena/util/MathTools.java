package com.rochatec.athena.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.rochatec.framework.exception.BadFormatException;

public class MathTools {

	public static String multiply(String value,String pFator)throws BadFormatException{
		BigDecimal base = Formatter.getDecimal().parse(value);
		BigDecimal fator = Formatter.getDecimal().parse(pFator);		
		return multiply(base, fator);
	}
	
	public static String multiply(String value,BigDecimal fator)throws BadFormatException{
		BigDecimal base = Formatter.getDecimal().parse(value);
		return multiply(base, fator);
	}
	
	public static String multiply(BigDecimal value,String pFator)throws BadFormatException{
		BigDecimal fator = Formatter.getDecimal().parse(pFator);
		return multiply(value, fator);
	}
	
	public static String multiply(BigDecimal value,BigDecimal pFator)throws BadFormatException{
		BigDecimal total = value.multiply(pFator);
		total.setScale(2,RoundingMode.HALF_EVEN);
		return Formatter.getDecimal().mask(total);
	}
	
	public static String percentage(BigDecimal pValue,BigDecimal pPercentage)throws BadFormatException{
		BigDecimal total = pValue.divide(new BigDecimal(100)).multiply(pPercentage);
		total.setScale(2,RoundingMode.HALF_EVEN);
		return Formatter.getDecimal().mask(total);
	}
	
	public static String percentage(BigDecimal pValue,String pPercentage)throws BadFormatException{
		BigDecimal percentage = Formatter.getDecimal().parse(pPercentage); 
		BigDecimal total = pValue.divide(new BigDecimal(100)).multiply(percentage);
		total.setScale(2,RoundingMode.HALF_EVEN);
		return Formatter.getDecimal().mask(total);
	}
	
	public static String percentage(String pValue,BigDecimal pPercentage)throws BadFormatException{
		BigDecimal value = Formatter.getDecimal().parse(pValue); 
		BigDecimal total = value.divide(new BigDecimal(100)).multiply(pPercentage);
		total.setScale(2,RoundingMode.HALF_EVEN);
		return Formatter.getDecimal().mask(total);
	}
	
	public static String percentage(String pValue,String pPercentage)throws BadFormatException{
		BigDecimal value = Formatter.getDecimal().parse(pValue);
		BigDecimal percentage = Formatter.getDecimal().parse(pPercentage);
		BigDecimal total = value.divide(new BigDecimal(100)).multiply(percentage);
		total.setScale(2,RoundingMode.HALF_EVEN);
		return Formatter.getDecimal().mask(total);
	}
	
}
