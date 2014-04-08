package com.rochatec.athena.eao.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.Menu;

@Local
public interface MenuEaoLocal {
	
	public List<Menu> getMenus();
}
