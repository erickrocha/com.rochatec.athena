package com.rochatec.pos.athena.app.math;

import com.rochatec.pos.athena.app.exception.BadFormatException;
import com.rochatec.pos.athena.app.formater.IFormarter;
import com.rochatec.pos.athena.utils.Messages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class Decimal implements IFormarter{
	
	private DecimalFormat df;
	
	public Decimal() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
		df = new DecimalFormat(Messages.getMessage("decimal.pattern"),symbols);
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
		return null;
	}

	@Override
	public String getMessage() {
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
