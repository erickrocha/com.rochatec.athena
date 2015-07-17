package com.rochatec.pos.athena.app.math;

import com.rochatec.pos.athena.app.exception.BadFormatException;
import com.rochatec.pos.athena.app.exception.WeightFormatException;
import com.rochatec.pos.athena.app.formater.IFormarter;
import com.rochatec.pos.athena.utils.Messages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Weight implements IFormarter{

	private NumberFormat nf;

	public Weight() {
		this(Messages.getMessage("mask.quantity"));
	}

	public Weight(String pattern) {
		nf = new DecimalFormat(pattern);
	}

	public String toString(BigDecimal value) {
		value.setScale(3, RoundingMode.HALF_EVEN);
		return nf.format(value);
	}

	public BigDecimal parse(String value)throws BadFormatException {
		try {
			BigDecimal decimal = new BigDecimal(value);
			decimal.setScale(3,RoundingMode.HALF_EVEN);			
			return decimal;
		}catch (NumberFormatException e){
			throw new WeightFormatException();
		}		 
	}

	@Override
	public String mask(String value) throws BadFormatException {
		BigDecimal decimal = parse(value);		
		decimal.setScale(3, RoundingMode.HALF_EVEN);
		return nf.format(decimal);
	}

	@Override
	public String mask(Object value) throws BadFormatException {
		if (value instanceof BigDecimal){
			BigDecimal decimal =  (BigDecimal)value;
			decimal.setScale(3, RoundingMode.HALF_EVEN);
			return nf.format(decimal);
		}
		return nf.format(value);
	}

	@Override
	public String unMask(String value) {
		return null;
	}

	@Override
	public String getMessage() {
		return null;
	}
}
