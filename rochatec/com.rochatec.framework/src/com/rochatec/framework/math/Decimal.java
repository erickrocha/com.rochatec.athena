package com.rochatec.framework.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;
import com.rochatec.framework.util.Message;

public class Decimal implements IFormarter{
	
	private DecimalFormat df;
	
	public Decimal() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
		df = new DecimalFormat(Message.getMessage("decimal.pattern"),symbols);
	}
	
	public String toString(BigDecimal value) {
		value.setScale(2, RoundingMode.HALF_EVEN);
		return df.format(value);
	}

	@Override
	public String mask(String value) throws BadFormatException {
		
		return null;
	}

	@Override
	public String mask(Object value) throws BadFormatException {
		return df.format(value);
	}

	@Override
	public String unMask(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal parse(String value) throws BadFormatException {
		try {
			BigDecimal bigDecimal = new BigDecimal(df.parse(value).doubleValue());
			bigDecimal.setScale(2,RoundingMode.HALF_EVEN);	
			return bigDecimal; 
		} catch (ParseException e) {
			throw new BadFormatException();
		}	
	}

}