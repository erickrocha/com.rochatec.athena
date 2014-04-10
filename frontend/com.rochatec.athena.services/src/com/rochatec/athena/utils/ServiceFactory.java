package com.rochatec.athena.utils;

import com.rochatec.athena.client.service.CRMClientService;
import com.rochatec.athena.client.service.HumanResourceClientService;
import com.rochatec.athena.client.service.InvoiceClientService;
import com.rochatec.athena.client.service.ManufactureClientService;
import com.rochatec.athena.client.service.ResourceClientService;
import com.rochatec.athena.client.service.SalesClientService;
import com.rochatec.athena.client.service.SecurityClientService;
import com.rochatec.athena.client.service.SupplyClientService;
import com.rochatec.athena.client.service.impl.CRMClientServiceImpl;
import com.rochatec.athena.client.service.impl.HumanResourceClientServiceImpl;
import com.rochatec.athena.client.service.impl.InvoiceClientserviceImpl;
import com.rochatec.athena.client.service.impl.ManufactureClientServiceImpl;
import com.rochatec.athena.client.service.impl.ResourceClientServiceImpl;
import com.rochatec.athena.client.service.impl.SalesClientServiceImpl;
import com.rochatec.athena.client.service.impl.SecurityClientServiceImpl;
import com.rochatec.athena.client.service.impl.SupplyClientServiceImpl;


public class ServiceFactory {
	
	private static ServiceFactory instance;
	
	private static HumanResourceClientService humanResourceClientService;
	private static CRMClientService crmClientService;
	private static SupplyClientService supplyClientService;
	private static ResourceClientService resourceClientService;
	private static SecurityClientService securityClientService;
	private static ManufactureClientService manufactureClientService;
	private static SalesClientService salesClientService;
	private static InvoiceClientService invoiceClientService;
	
	private ServiceFactory() {
	}
	
	public static ServiceFactory getInstance(){
		if (instance == null){
			instance = new ServiceFactory();
			humanResourceClientService =  new HumanResourceClientServiceImpl();
			crmClientService = new CRMClientServiceImpl();
			supplyClientService = new SupplyClientServiceImpl();
			resourceClientService = new ResourceClientServiceImpl();
			securityClientService =  new SecurityClientServiceImpl();
			manufactureClientService  = new ManufactureClientServiceImpl();
			salesClientService = new SalesClientServiceImpl();
			invoiceClientService = new InvoiceClientserviceImpl();
		}
		return instance;
	}
	
	public HumanResourceClientService getHumanResourceClientService(){
		return humanResourceClientService != null ? humanResourceClientService : new HumanResourceClientServiceImpl();
	}
	
	public SecurityClientService getSecurityClientService(){
		return securityClientService != null ? securityClientService : new SecurityClientServiceImpl();
	}
	
	public CRMClientService getCrmClientService(){
		 return crmClientService != null ? crmClientService : new CRMClientServiceImpl();
	}
	
	public SupplyClientService getSupplyClientService(){
		return supplyClientService != null ? supplyClientService : new SupplyClientServiceImpl();
	}
	
	public ResourceClientService getResourceClientService(){
		return resourceClientService != null ? resourceClientService : new ResourceClientServiceImpl();
	}
	
	public ManufactureClientService getManufactureClientService(){
		return manufactureClientService != null ? manufactureClientService : new ManufactureClientServiceImpl();
	}
	
	public SalesClientService getSalesClientService(){
		return salesClientService != null ? salesClientService : new SalesClientServiceImpl();
	}
		
	public InvoiceClientService getInvoiceClientService(){
		return invoiceClientService != null ? invoiceClientService : new InvoiceClientserviceImpl();
	}
		
}
