package com.menu.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="menu")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuId;
	private String menuDescription;
	private String menuName;
	private String subMenuOf;
	private Boolean isActive;

    public Boolean isActive() {
		return isActive;
	}
	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "parentMenu")
    private List<Item> listOfItems;
    
	public String getSubMenuOf() {
		return subMenuOf;
	}
	public void setSubMenuOf(String subMenuOf) {
		this.subMenuOf = subMenuOf;
	}
	public List<Item> getListOfItems() {
		return listOfItems;
	}
	public void setListOfItems(List<Item> listOfItems) {
		this.listOfItems = listOfItems;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId; 
	}
	public String getMenuDescription() {
		return menuDescription;
	}
	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getSubMenu() {
		return subMenuOf;
	}
	public void setSubMenu(String subMenu) {
		this.subMenuOf = subMenu;
	}
}
