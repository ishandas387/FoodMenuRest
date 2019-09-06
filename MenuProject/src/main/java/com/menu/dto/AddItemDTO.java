package com.menu.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddItemDTO {
	@NotNull(message = "Item Name can not be null!")
	private String itemName;
	@NotNull(message = "Item Name can not be null!")
	private String itemDescription;
	@NotNull(message = "Item price can not be null! and - separated for currency")
	private String itemPriceWithCurrency;
	
	@NotNull
	@Max(5)
	private Long rating=0l;
	
	private String parentMenuName;

	public String getParentMenu() {
		return parentMenuName;
	}

	public void setParentMenu(String parentMenu) {
		this.parentMenuName = parentMenu;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemPriceWithCurrency() {
		return itemPriceWithCurrency;
	}

	public void setItemPriceWithCurrency(String itemPriceWithCurrency) {
		this.itemPriceWithCurrency = itemPriceWithCurrency;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}
	
}
