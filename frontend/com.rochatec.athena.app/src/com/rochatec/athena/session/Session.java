package com.rochatec.athena.session;

import java.util.HashMap;
import java.util.Map;

import com.rochatec.athena.exceptions.UserException;
import com.rochatec.athena.exceptions.UserNotLoggedException;
import com.rochatec.athena.model.User;
import com.rochatec.athena.util.ATHENA;
import com.rochatec.framework.context.ISecurityPolicy;



public class Session {
	private static Map<Long,Object> map;
	
	static{
		map = new HashMap<Long, Object>();
	}
	public Object getAttribute(Long attribute){
		return map.get(attribute);
	}
	
	public void setAttribute(Long key,Object value){
		map.put(key,value);
	}	
	
	public ISecurityPolicy getSecurityPolicy() throws UserException{
		if ( getAttribute(ATHENA.CURRENT_USER) !=null){
			User user = (User) getAttribute(ATHENA.CURRENT_USER);
			ISecurityPolicy policy = new SecurityPolicy(user);
			return policy;
		}else{
			throw new UserNotLoggedException();
		}
	}
}
