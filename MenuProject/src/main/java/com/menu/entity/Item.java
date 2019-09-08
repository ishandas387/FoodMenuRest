package com.menu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	private String name;
	private String description;
	private String price;
	@Column(nullable = false)
	@NotNull(message = "Rating can not be null!")
	private Integer rating = 0;
	String avaiableDays;
	private String startSchedule;
	private String endSchedule;
	private String startDate;
	private String endDate;
	@ManyToOne
	@JoinColumn(name ="menu_id")
	private Menu parentMenu;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


	public String getStartSchedule() {
		return startSchedule;
	}

	public void setStartSchedule(String startSchedule) {
		this.startSchedule = startSchedule;
	}

	public String getEndSchedule() {
		return endSchedule;
	}

	public void setEndSchedule(String endSchedule) {
		this.endSchedule = endSchedule;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAvaiableDays() {
		return avaiableDays;
	}

	public void setAvaiableDays(String avaiableDays) {
		this.avaiableDays = avaiableDays;
	}


}
