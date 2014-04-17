package com.rochatec.athena.session;

import java.util.HashMap;
import java.util.Map;

import com.rochatec.athena.exceptions.UserException;
import com.rochatec.athena.exceptions.UserNotLoggedException;
import com.rochatec.athena.model.User;



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
	
	public SecurityPolicy getSecurityPolicy() throws UserException{
		if ( getAttribute(IAppConstants.CURRENT_USER) !=null){
			User user = (User) getAttribute(IAppConstants.CURRENT_USER);
			SecurityPolicy policy = new SecurityPolicy(user);
			return policy;
		}else{
			throw new UserNotLoggedException();
		}
	}
}
