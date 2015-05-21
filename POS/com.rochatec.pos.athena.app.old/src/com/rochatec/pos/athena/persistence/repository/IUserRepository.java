package com.rochatec.pos.athena.persistence.repository;

import java.util.List;

import com.rochatec.pos.athena.persistence.model.User;


public interface IUserRepository {
	
	public User persist(User user);
	
	public User findById(Long id);
	
	public User findByLogin(String username);
	
	public List<User> findByName(String name);
	
	public void remove(User user);
	
	public boolean isExists(String userName);
	
}
