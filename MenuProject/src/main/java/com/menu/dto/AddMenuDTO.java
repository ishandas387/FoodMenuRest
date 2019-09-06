package com.menu.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddMenuDTO {

	@NotNull(message = "Menu Name can not be null!")
	private String menuName;
	@NotNull(message = "Menu Description can not be null!")
	private String menuDescription;
	private String parentMenu;
	private List<AddItemDTO> listOfItems= new ArrayList<>();
	
	public List<AddItemDTO> getListOfItems() {
		return listOfItems;
	}
	public void setListOfItems(List<AddItemDTO> listOfItems) {
		this.listOfItems = listOfItems;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuDescription() {
		return menuDescription;
	}
	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}
	public String getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}
	
}
