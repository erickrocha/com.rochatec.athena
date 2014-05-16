package com.rochatec.framework.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.exception.WeightFormatException;
import com.rochatec.framework.formater.IFormarter;
import com.rochatec.framework.util.Message;

public class Weight implements IFormarter{

	private NumberFormat nf;

	public Weight() {
		this(Message.getMessage("mask.quantity"));
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
