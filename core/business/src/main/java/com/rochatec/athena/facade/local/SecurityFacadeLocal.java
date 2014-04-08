package com.rochatec.athena.facade.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.exceptions.UserLoginException;
import com.rochatec.athena.model.Profile;
import com.rochatec.athena.model.Role;
import com.rochatec.athena.model.User;

@Local
public interface SecurityFacadeLocal {

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
	
	public User login(String login, String password) throws UserLoginException ;
	
	public void updatePassword(String login, String oldPassword, String newPassword) throws UserLoginException;
	
	public Profile persist(Profile profile);
	
	public void remove(Profile profile);
	
}
