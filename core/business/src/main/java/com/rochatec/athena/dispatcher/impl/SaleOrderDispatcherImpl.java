package com.rochatec.athena.dispatcher.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.rochatec.athena.dispatcher.SaleOrderDispatcher;
import com.rochatec.athena.events.SaleOrderEvent;
import com.rochatec.athena.listeners.SaleOrderListener;
import com.rochatec.athena.model.SaleOrder;

@Stateless
public class SaleOrderDispatcherImpl implements SaleOrderDispatcher{
	
	private List<SaleOrderListener> listeners = new ArrayList<SaleOrderListener>();
	
	@EJB
	private SaleOrderListener saleOrderListener;

	@PostConstruct
	public void postConstruct(){
		listeners.add(saleOrderListener);
	}
	
	@Override
	public void addSaleOrderListener(SaleOrderListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeSaleOrderListener(SaleOrderListener listener) {
		this.listeners.remove(listener);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(SaleOrder saleOrder) {
		final SaleOrderEvent event = new SaleOrderEvent(saleOrder);
		event.saleOrder = saleOrder;
		event.action = saleOrder.getStatus();
		
		Collection<SaleOrderListener> copy;
		synchronized (this) {
			copy = ((Collection<SaleOrderListener>) (((ArrayList<SaleOrderListener>)listeners).clone()));
		}
		
		for (final SaleOrderListener listener : copy){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					listener.save(event);
				}
			});
			thread.setName("Sale Order : "+event.action);
			thread.start();	
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void generate(SaleOrder saleOrder) {
		final SaleOrderEvent event = new SaleOrderEvent(saleOrder);
		event.saleOrder = saleOrder;
		event.action = saleOrder.getStatus();
		Collection<SaleOrderListener> copy;
		synchronized (this) {
			copy = ((Collection<SaleOrderListener>) (((ArrayList<SaleOrderListener>)listeners).clone()));
		}
		
		for (final SaleOrderListener listener : copy){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					listener.generate(event);
				}
			});
			thread.setName("Sale Order : "+event.action);
			thread.start();			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void finish(SaleOrder saleOrder) {
		final SaleOrderEvent event = new SaleOrderEvent(saleOrder);
		event.saleOrder = saleOrder;
		event.action = saleOrder.getStatus();
		
		Collection<SaleOrderListener> copy;
		synchronized (this) {
			copy = ((Collection<SaleOrderListener>) (((ArrayList<SaleOrderListener>)listeners).clone()));
		}
		
		for (final SaleOrderListener listener : copy){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					listener.finish(event);	
				}
			});
			thread.setName("Sale Order : "+event.action);
			thread.start();
		}
	}

}
