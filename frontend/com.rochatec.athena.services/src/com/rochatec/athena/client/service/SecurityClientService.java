package com.rochatec.athena.client.service;

import java.util.List;

import com.rochatec.athena.exceptions.UserLoginException;
import com.rochatec.athena.exceptions.UserNotLoggedException;
import com.rochatec.athena.model.Profile;
import com.rochatec.athena.model.Role;
import com.rochatec.athena.model.User;

public interface SecurityClientService {

	public User persist(User user);

	public User findUserById(Long id);

	public User findUserByLogin(String username);

	public List<User> findUserByName(String name);

	public void remove(User user);

	public List<Role> findAllRoles();

	public Role findRoleByKey(String key);

	public Profile findProfileById(Long id);

	public List<Profile> findByProfileName(String name);

	public List<Profile> findAllProfiles();
	
	public Profile persist(Profile profile);
	
	public void remove(Profile profile);
	
	public User login(String login, String password) throws UserLoginException, UserNotLoggedException ;
	
	public void updatePassword(String login, String oldPassword,String newPassword) throws UserLoginException;

}
