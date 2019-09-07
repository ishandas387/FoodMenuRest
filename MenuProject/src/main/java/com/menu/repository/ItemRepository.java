package com.menu.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.menu.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
	
	public List<Item> findByName(String itemName);
	@Query("From Item i where i.parentMenu.menuId in (:menuId)")
	public List<Item> getItemListBasedOnMenu(@Param("menuId") Set<Long> menuId);

}