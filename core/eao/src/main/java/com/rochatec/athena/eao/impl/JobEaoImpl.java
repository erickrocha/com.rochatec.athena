package com.rochatec.athena.eao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.rochatec.athena.eao.local.JobEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.Job;

@Stateless
public class JobEaoImpl extends GenericEao<Job, Serializable> implements
		JobEaoLocal {

	public Job findById(Integer id) {
		Job job = super.findById(id);
		return job;
	}

	@SuppressWarnings("unchecked")
	public List<Job> findByName(String name) {
		Query query = getEntityManager().createNamedQuery("Job.findByName");
		query.setParameter("name", name + "%");
		List<Job> jobs = (List<Job>) query.getResultList();
		return jobs;
	}

	@SuppressWarnings("unchecked")
	public List<Job> findAll() {
		Query query = getEntityManager().createNamedQuery("Job.findAll");
		List<Job> jobs = ((List<Job>) query.getResultList());
		return jobs;
	}

}
