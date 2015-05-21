package com.rochatec.pos.athena.tools;

import com.rochatec.framework.formater.IFormarter;
import com.rochatec.framework.formater.impl.DateFormaterImpl;
import com.rochatec.framework.formater.impl.PhoneFormaterImpl;
import com.rochatec.framework.formater.impl.SocialSecurityFormaterImpl;
import com.rochatec.framework.formater.impl.ZipCodeFormaterImpl;
import com.rochatec.framework.math.Currency;
import com.rochatec.framework.math.Decimal;
import com.rochatec.framework.math.None;
import com.rochatec.framework.math.Percentage;
import com.rochatec.framework.math.Weight;

public class Formatter {

	private static Currency currency;
	private static Weight weight;
	private static Percentage percentage;
	private static IFormarter zipCode;
	private static IFormarter socialSecurity;
	private static IFormarter phone;
	private static IFormarter date;
	private static Decimal decimal;
	private static None none;

	static {
		currency = new Currency();
		weight = new Weight();
		percentage = new Percentage();
		zipCode = new ZipCodeFormaterImpl();
		socialSecurity = new SocialSecurityFormaterImpl();
		phone = new PhoneFormaterImpl();
		date = new DateFormaterImpl();
		decimal = new Decimal();
		none = new None();
	}

	public static Currency getCurrency() {
		return currency != null ? currency : new Currency();
	}

	public static Weight getWeight() {
		return weight != null ? weight : new Weight();
	}

	public static Percentage getPercentage() {
		return percentage != null ? percentage : new Percentage();
	}
	
	public static IFormarter getZipCode(){
		return zipCode != null ? zipCode : new ZipCodeFormaterImpl();
	}
	
	public static IFormarter getSocialSecurity(){
		return socialSecurity != null ? socialSecurity : new SocialSecurityFormaterImpl();
	}
	
	public static IFormarter getPhone(){
		return phone != null ? phone : new PhoneFormaterImpl();
	}
	
	public static IFormarter getDate(){
		return date != null ? date : new DateFormaterImpl();
	}
	
	public static Decimal getDecimal(){
		return decimal != null ? decimal : new Decimal();
	}
	
	public static None getNone(){
		return none != null ? none : new None();
	}

}
