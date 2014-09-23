package com.rochatec.athena.pdv.lib.service;

import java.util.Date;

import com.rochatec.athena.pdv.lib.model.Increase;
import com.rochatec.athena.pdv.lib.model.IncreaseOrDiscount;
import com.rochatec.athena.pdv.lib.model.Item;
import com.rochatec.athena.pdv.lib.model.Payment;



public interface IPrinterService {
	
	public int openSerialPort();
	public int openPort(Integer portNumber);
	
	public int openTicket(String socialSecurity);
	
	public int sellItem(Item item);
	
	public int cancelPreviousItem();
	public int cancelItem(String itemNumber);
	public int cancelTicket();
	
	
	
	public int makePayment(Payment payment);
	public int switchPayment(Payment source,Payment target);
	
	public int increase(Increase increase);
	
	public int startCloseTicket(IncreaseOrDiscount increaseOrDiscount);
	public int closeTicket(Payment payment,IncreaseOrDiscount increaseOrDiscount,String message);
	public int closeTicketSummarized(Payment payment,String message);
	public int endCloseTicket(String message);
	
	public int lineSpace(int dots);
	public int addTax(String aliquota, int vinculo);
	public int setRounding();
	public int setTruncate();
	public int setSummerHour();
	
	public int bleeding(String value);
	public int supply(String valor,String payment);
	
	public int readingMemoryFiscalByDate(String beginDate, String endDate);
	public int readingMemoryFiscalByReduction(String beginReduction, String endReduction);
	public int readingMemoryFiscalSerialByDate(Date beginDate,Date endDate);
	public int readingMemoryFiscalSerialByReduction(String beginReduction, String endReduction);
	public int readingX();
	public int readingXSerial();
	public int reductionZ(String data, String cHora);
	
	
	
	
	
	

}
