package com.rochatec.athena.pdv.lib.printer;

public class ECFPrinter {

	private ECFPrinterType printerType;
	private String printerId;

	public ECFPrinterType getPrinterType() {
		return printerType;
	}

	public void setPrinterType(ECFPrinterType printerType) {
		this.printerType = printerType;
	}

	public String getPrinterId() {
		return printerId;
	}

	public void setPrinterId(String printerId) {
		this.printerId = printerId;
	}

}
