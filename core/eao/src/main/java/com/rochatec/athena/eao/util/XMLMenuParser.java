package com.rochatec.athena.eao.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.rochatec.athena.model.Menu;
import com.rochatec.athena.model.Role;
import com.rochatec.athena.model.SubMenu;
import com.rochatec.athena.eao.local.RoleEaoLocal;

public class XMLMenuParser extends DefaultHandler{
	
	private SAXParserFactory factory;
	private SubMenu subMenu;
	private List<SubMenu> subMenus = new ArrayList<SubMenu>();
	private Menu mainMenu;
	private Map<String,Menu> menus = new HashMap<String, Menu>();
	private static final String MENU	=	"menu";
	private static final String SUBMENU	=	"sub-menu";
	private static final String ROLE	=	"role";
	private static final String NAME	=	"name";
	private static final String LABEL	= 	"label";
	private static final String PARENT	= 	"parent";
	private static final String ACTION	= 	"action";
	private static final String INDEX	= 	"index";
	private static final String SEPARATOR	=	"separator";
	
	
	
	private RoleEaoLocal roleEaoLocal;
	
	public XMLMenuParser(RoleEaoLocal roleEaoLocal) {
		this.factory = SAXParserFactory.newInstance();
		this.factory.setValidating(true);
		this.roleEaoLocal = roleEaoLocal;
	}
	
	public void startElement(String namespaceURI, String localName,
			String name, Attributes attrs){
		if (readMenu(name, attrs)){
			return;
		}
		if (readSubMenu(name, attrs)){
			return;
		}
		if (readSeparator(name,attrs)){
			return;
		}	
	}
	
	
	
	public void endElement(String namespaceURI, String localName, String name)
			throws SAXException {
		if (name.equals(MENU)){
			this.mainMenu = null;
		}else{
			this.subMenu = null;
		}
	}

	public SAXParserFactory getFactory() {
		return factory;
	}
	
	private boolean readMenu(String name,Attributes attrs){
		if (name.equals(MENU)){
			String label = "";
			String nameMenu = "";
			Integer index = 0;
			for (int i = 0; i < attrs.getLength(); i++){
				String nattr = attrs.getQName(i);
				String val = attrs.getValue(i);
				if (nattr.equals(NAME)){
					nameMenu = val;
				}
				if (nattr.equals(LABEL)){
					label = val;
				}
				if (nattr.equals(INDEX)){
					index = Integer.valueOf(val);
				}
			}
			this.mainMenu = new Menu(index, nameMenu, label);
			this.menus.put(mainMenu.getName(),this.mainMenu);
			return true;
		}
		return false;
	}

	public Map<String,Menu> getMenus() {
		return menus;
	}
	
	private boolean readSubMenu(String name,Attributes attrs){
		if (name.equals(SUBMENU)){
			this.subMenu = new SubMenu();
			for (int i = 0; i < attrs.getLength(); i++){
				String nattr = attrs.getQName(i);
				String val = attrs.getValue(i);
				if (nattr.equals(ROLE)){
					Role role = roleEaoLocal.findByKey(val);
					this.subMenu.setRole(role);
				}
				if (nattr.equals(NAME)){
					this.subMenu.setName(val);
				}
				if (nattr.equals(LABEL)){
					this.subMenu.setLabel(val);
				}
				if (nattr.equals(PARENT)){
					this.subMenu.setParent(menus.get(val));
				}
				if (nattr.equals(ACTION)){
					this.subMenu.setActionUri(val);
				}
			}
			subMenus.add(subMenu);
			return true;
		}
		return false;
	}

	public List<SubMenu> getSubMenus() {
		return subMenus;
	}
	
	private boolean readSeparator(String name,Attributes attrs){
		if (name.equals(SEPARATOR)){
			this.subMenu = new SubMenu();
			this.subMenu.setSeparator(true);
			subMenus.add(this.subMenu);
		}
		return false;
	}
	 
}
