package com.rochatec.athena.pdv.lib.service.impl;

import java.text.DateFormat;
import java.util.Date;

import bemajava.BemaString;
import bemajava.Bematech;

import com.rochatec.athena.pdv.lib.model.Increase;
import com.rochatec.athena.pdv.lib.model.IncreaseOrDiscount;
import com.rochatec.athena.pdv.lib.model.Item;
import com.rochatec.athena.pdv.lib.model.Payment;
import com.rochatec.athena.pdv.lib.service.IPrinterService;

public class BematechPrinterServiceImpl implements IPrinterService{

	@Override
	public int openSerialPort() {
		return Bematech.AbrePortaSerial();
	}

	@Override
	public int openPort(Integer portNumber) {
		return Bematech.AbrePorta(portNumber.intValue());
	}

	@Override
	public int openTicket(String socialSecurity) {
		return Bematech.AbreCupom(socialSecurity);
	}

	@Override
	public int sellItem(Item item) {
		return 0;
	}

	@Override
	public int cancelPreviousItem() {
		return Bematech.CancelaItemAnterior();
	}

	@Override
	public int cancelItem(String itemNumber) {
		return Bematech.CancelaItemGenerico(itemNumber);
	}

	@Override
	public int cancelTicket() {
		return Bematech.CancelaCupom();
	}

	@Override
	public int makePayment(Payment payment) {
		return Bematech.EfetuaFormaPagamento(payment.getName(),payment.getValue().toString());
	}

	@Override
	public int switchPayment(Payment source, Payment target) {
		return Bematech.EstornoFormasPagamento(source.getName(),target.getName(),target.getValue().toString());
	}

	@Override
	public int increase(Increase increase) {
		BemaString value = new BemaString();
		value.buffer = increase.getValueString();
		return Bematech.Acrescimos(value);
	}

	@Override
	public int startCloseTicket(IncreaseOrDiscount increaseOrDiscount) {
		return Bematech.IniciaFechamentoCupom(increaseOrDiscount.getType().toString(),increaseOrDiscount.getType().toString(),increaseOrDiscount.getValueString());
	}

	@Override
	public int closeTicket(Payment payment,
			IncreaseOrDiscount increaseOrDiscount, String message) {
		return 0;
	}

	@Override
	public int closeTicketSummarized(Payment payment, String message) {		
		return Bematech.FechaCupomResumido(payment.getName(),message);
	}

	@Override
	public int endCloseTicket(String message) {		
		return Bematech.TerminaFechamentoCupom(message);
	}

	@Override
	public int lineSpace(int dots) {		
		return Bematech.EspacoEntreLinhas(dots);
	}

	@Override
	public int addTax(String aliquota, int vinculo) {		
		return Bematech.ProgramaAliquota(aliquota, vinculo);
	}

	@Override
	public int setRounding() {
		return Bematech.ProgramaArredondamento();
	}

	@Override
	public int setTruncate() {
		return Bematech.ProgramaTruncamento();
	}

	@Override
	public int setSummerHour() {
		return Bematech.ProgramaHorarioVerao();
	}

	@Override
	public int bleeding(String value) {
		return Bematech.Sangria(value);
	}

	@Override
	public int supply(String valor, String payment) {
		return Bematech.Suprimento(valor, payment);
	}

	@Override
	public int readingMemoryFiscalByDate(String beginDate, String endDate) {
		DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);		
		return Bematech.LeituraMemoriaFiscalData(format.format(beginDate),format.format(endDate));
	}

	@Override
	public int readingMemoryFiscalByReduction(String beginreduction,String endReduction) {		
		return Bematech.LeituraMemoriaFiscalReducao(beginreduction, endReduction);
	}

	@Override
	public int readingMemoryFiscalSerialByDate(Date beginDate, Date endDate) {
		DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);		
		return Bematech.LeituraMemoriaFiscalSerialData(format.format(beginDate),format.format(endDate));
	}

	@Override
	public int readingMemoryFiscalSerialByReduction(String cReducaoInicial,
			String cReducaoFinal) {
		return Bematech.LeituraMemoriaFiscalSerialReducao(cReducaoInicial, cReducaoFinal);
	}

	@Override
	public int readingX() {
		return Bematech.LeituraX();
	}

	@Override
	public int readingXSerial() {
		return Bematech.LeituraXSerial();
	}

	@Override
	public int reductionZ(String data, String cHora) {		
		return Bematech.ReducaoZ(data,cHora);
	}

}
