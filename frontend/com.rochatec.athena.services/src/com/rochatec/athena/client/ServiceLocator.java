package com.rochatec.athena.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import com.rochatec.athena.facade.impl.CRMFacadeImpl;
import com.rochatec.athena.facade.impl.HumanResourceFacadeImpl;
import com.rochatec.athena.facade.impl.InvoiceFacadeImpl;
import com.rochatec.athena.facade.impl.ManufactureFacadeImpl;
import com.rochatec.athena.facade.impl.ResourceFacadeImpl;
import com.rochatec.athena.facade.impl.SalesFacadeImpl;
import com.rochatec.athena.facade.impl.SecurityFacadeImpl;
import com.rochatec.athena.facade.impl.SupplyFacadeImpl;
import com.rochatec.athena.facade.remote.CRMFacadeRemote;
import com.rochatec.athena.facade.remote.HumanResourceFacadeRemote;
import com.rochatec.athena.facade.remote.InvoiceFacadeRemote;
import com.rochatec.athena.facade.remote.ManufactureFacadeRemote;
import com.rochatec.athena.facade.remote.ResourceFacadeRemote;
import com.rochatec.athena.facade.remote.SalesFacadeRemote;
import com.rochatec.athena.facade.remote.SecurityFacadeRemote;
import com.rochatec.athena.facade.remote.SupplyFacadeRemote;

public class ServiceLocator {

	private final String prefix = "ejb:";

	private final String ear_name = "com.athena.enterprise-1.0.0-SNAPSHOT";

	private final String ejb_jar = "business-1.0.0-SNAPSHOT";

	private final String separator = "/";

	private static Logger LOGGER = Logger.getLogger(ServiceLocator.class);
	private static ServiceLocator locator;

	private Context context;

	static {
		locator = new ServiceLocator();
	}

	public static ServiceLocator getInstance() {
		if (locator == null) {
			locator = new ServiceLocator();
		}
		return locator;
	}

	private ServiceLocator() {
		try {
			Properties properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
			context = new InitialContext(properties);
		} catch (NamingException e) {
			LOGGER.error(e.getMessage());
		}

	}
	
	public HumanResourceFacadeRemote getHumanResourceFacadeRemote(){
		String url = prefix+ear_name+separator+ejb_jar+separator+HumanResourceFacadeImpl.class.getSimpleName()+"!"+HumanResourceFacadeRemote.class.getName();
		HumanResourceFacadeRemote remote = null;
		try{
			remote = (HumanResourceFacadeRemote)context.lookup(url);
		}catch (NamingException e) {
			LOGGER.error(e);
		}
		return remote;
	}
	
	public SecurityFacadeRemote getSecurityFacadeRemote(){
		String url = prefix+ear_name+separator+ejb_jar+separator+SecurityFacadeImpl.class.getSimpleName()+"!"+SecurityFacadeRemote.class.getName();
		SecurityFacadeRemote remote = null;
		try{
			remote = (SecurityFacadeRemote)context.lookup(url);
		}catch (Exception e) {
			LOGGER.error(e);
		}
		return remote;
	}
	
	public CRMFacadeRemote getCrmFacadeRemote(){
		String url = prefix+ear_name+separator+ejb_jar+separator+CRMFacadeImpl.class.getSimpleName()+"!"+CRMFacadeRemote.class.getName();
		CRMFacadeRemote remote = null;
		try{
			remote = (CRMFacadeRemote)context.lookup(url);
		}catch (NamingException e) {
			LOGGER.error(e);
		}
		return remote;
	}
	
	public SupplyFacadeRemote getSupplyFacadeRemote(){
		String url = prefix+ear_name+separator+ejb_jar+separator+SupplyFacadeImpl.class.getSimpleName()+"!"+SupplyFacadeRemote.class.getName();
		SupplyFacadeRemote remote = null;
		try{
			remote = (SupplyFacadeRemote)context.lookup(url);
		}catch (NamingException e) {
			LOGGER.error(e);
		}
		return remote;
	}
	
	public SalesFacadeRemote getSalesFacadeRemote(){
		String url = prefix+ear_name+separator+ejb_jar+separator+SalesFacadeImpl.class.getSimpleName()+"!"+SalesFacadeRemote.class.getName();
		SalesFacadeRemote remote = null;
		try{
			remote = (SalesFacadeRemote)context.lookup(url);
		}catch (NamingException e) {
			LOGGER.error(e);
		}
		return remote;
	}
	
	public ResourceFacadeRemote getResourceFacadeRemote(){
		String url = prefix+ear_name+separator+ejb_jar+separator+ResourceFacadeImpl.class.getSimpleName()+"!"+ResourceFacadeRemote.class.getName();
		ResourceFacadeRemote remote = null;
		try{
			remote = (ResourceFacadeRemote)context.lookup(url);
		}catch (NamingException e) {
			LOGGER.error(e);
		}
		return remote;
	}
	
	public ManufactureFacadeRemote getManufactureFacadeRemote(){
		String url = prefix+ear_name+separator+ejb_jar+separator+ManufactureFacadeImpl.class.getSimpleName()+"!"+ManufactureFacadeRemote.class.getName();
		ManufactureFacadeRemote remote = null;
		try{
			remote = (ManufactureFacadeRemote)context.lookup(url);
		}catch (NamingException e) {
			LOGGER.error(e);
		}
		return remote;
	}
	
	public InvoiceFacadeRemote getInvoiceFacadeRemote(){
		String url = prefix+ear_name+separator+ejb_jar+separator+InvoiceFacadeImpl.class.getSimpleName()+"!"+InvoiceFacadeRemote.class.getName();
		InvoiceFacadeRemote remote = null;
		try{
			remote = (InvoiceFacadeRemote)context.lookup(url);
		}catch (NamingException e) {
			LOGGER.error(e);
		}
		return remote;
	}
}
