package com.rochatec.athena.eao.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Job;

@Local
public interface JobEaoLocal {
	
	public Job persist(Job job);
	
	public void remove(Job job);
	
	public Job findById(Integer id);
	
	public List<Job> findByName(String name);
	
	public List<Job> findAll();
	
}
