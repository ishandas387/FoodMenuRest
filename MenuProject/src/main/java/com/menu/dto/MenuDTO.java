package com.menu.dto;

import java.util.List;

public class MenuDTO {
	
	private Long id;
	private String name;
	private String description;
	List<MenuDTO> subMenu;
	List<ItemDTO> items;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<MenuDTO> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<MenuDTO> subMenu) {
		this.subMenu = subMenu;
	}
	public List<ItemDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}
	

}
