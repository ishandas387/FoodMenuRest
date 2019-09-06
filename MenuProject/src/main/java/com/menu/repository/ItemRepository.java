package com.menu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.menu.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
	
	public List<Item> findByName(String itemName);

}