package com.rochatec.athena.session;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.rochatec.athena.model.Role;
import com.rochatec.athena.model.User;
import com.rochatec.framework.context.ISecurityPolicy;

public class SecurityPolicy implements ISecurityPolicy{
	
	private User userLogged;
	private Map<String,Role> mergedRoles = new HashMap<String, Role>();
	
	public SecurityPolicy(User user) {
		this.userLogged = user;
		mergeRoles();
	}
	
	/**
	 * Faz um merge entre as permiss�es do perfil com as permiss�es do usu�rio
	 */
	private void mergeRoles(){
		Set<Role> merged = new HashSet<Role>(userLogged.getRoles());
		merged.addAll(userLogged.getProfile().getRoles());
		Role role = new Role("ALL","CLOSE","Fechar Aplica��o");
		merged.add(role);
		for (Role r : merged){
			mergedRoles.put(r.getKey(),r);
		}
	}
	
	
	/**
	 * Verifica se o usu�rio logado possui permiss�o para acessar o recurso solicitado
	 * @param key
	 * @return
	 */
	public boolean isHavePermission(String key){
		return mergedRoles.get(key) != null ? true : false;
	}
	
	
	
}
