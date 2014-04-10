package com.rochatec.framework.formater.impl;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;
import com.rochatec.framework.util.TextFormat;

public class TimeFormaterImpl implements IFormarter{
	
	private TextFormat format;
	
	public TimeFormaterImpl() {
		format = new TextFormat("##:##");
	}
	
	@Override
	public String mask(String value) throws BadFormatException {
		return format.mask(value);
	}

	@Override
	public String unMask(String value) {
		return format.unMask(value);
	}

	@Override
	public String getMessage() {
		return "";
	}

	@Override
	public String mask(Object value) throws BadFormatException {
		if (value instanceof String){
			return format.mask(value.toString());
		}
		return value.toString();
	}

	@Override
	public Object parse(String value) throws BadFormatException {
		String masked = mask(value);
		validate(masked.split(":"));
		return mask(value);
		
	}
	
	private void validate(String[] times)throws BadFormatException{
		if (times.length == 2){
			try{
				int hour = Integer.valueOf(times[0]);
				int minute = Integer.valueOf(times[1]);
				validate(hour,minute,00);
			}catch (NumberFormatException e) {
				throw new BadFormatException();
			}
		}else if (times.length == 3){
			try{
				int hour = Integer.valueOf(times[0]);
				int minute = Integer.valueOf(times[1]);
				int second = Integer.valueOf(times[2]);
				validate(hour,minute,second);
			}catch (NumberFormatException e) {
				throw new BadFormatException();
			}
		}else{
			throw new BadFormatException();
		}
	}
	
	private void validate(int hour,int minute,int second)throws BadFormatException{
		if (hour < 0 || hour > 23){
			throw new BadFormatException();
		}
		if (minute < 0 || minute > 59){
			throw new BadFormatException();
		}
		if (second < 0 || second > 59){
			throw new BadFormatException();
		}
	}

}
