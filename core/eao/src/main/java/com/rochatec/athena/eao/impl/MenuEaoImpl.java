package com.rochatec.athena.eao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

import org.jboss.logging.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;

import com.rochatec.athena.eao.local.MenuEaoLocal;
import com.rochatec.athena.eao.local.RoleEaoLocal;
import com.rochatec.athena.eao.util.XMLMenuParser;
import com.rochatec.athena.model.Menu;
import com.rochatec.athena.model.SubMenu;

@Stateless
public class MenuEaoImpl implements MenuEaoLocal{
	
	private Logger logger = Logger.getLogger(MenuEaoImpl.class);
	
	private  XMLMenuParser par;
	
	@EJB
	private RoleEaoLocal roleEaoLocal;  
	
	@Override
	public List<Menu> getMenus() {
		String path = System.getProperty("jboss.server.base.dir");
		readfile(path+"/configuration/menus.xml");
		
		List<Menu> menus = new ArrayList<Menu>();
		Map<String,Menu> map  = par.getMenus();
		List<SubMenu> subMenus = par.getSubMenus();
		
		for (String key : map.keySet()){
			Menu menu = map.get(key);
			for (SubMenu subMenu : subMenus){
				if (subMenu.isSeparator() || subMenu.getParent().equals(menu)){
						menu.addSubMenu(subMenu);
				}
			}
			
			menus.add(menu);
		}
		Collections.sort(menus,new Comparator<Menu>() {

			@Override
			public int compare(Menu o1, Menu o2) {
				return o1.getIndex().compareTo(o2.getIndex());
			}
		});;
		return menus;
	}
	
	public boolean readfile(String path){
		try{
			File file = new File(path);
			logger.error(file.getAbsolutePath());
			logger.error(file.getCanonicalPath());
			par = new XMLMenuParser(roleEaoLocal);
			SAXParser saxParser = par.getFactory().newSAXParser();
			saxParser.getXMLReader().setFeature("http://xml.org/sax/features/namespaces", true);
			saxParser.getXMLReader().setFeature("http://xml.org/sax/features/namespace-prefixes", true);
			saxParser.getXMLReader().setFeature("http://xml.org/sax/features/string-interning", true);
			saxParser.parse(file,par);
			return true;
		}catch (SAXParseException e) {
			logger.error(e.getMessage());
		} catch (SAXNotRecognizedException e) {
			logger.error(e.getMessage());
		} catch (SAXNotSupportedException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

}
