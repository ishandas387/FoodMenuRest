package com.menu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.menu.entity.Menu;

public interface MenuRepository extends CrudRepository<Menu, Long> {

	public List<Menu> findByIsActive(boolean flag);
	public List<Menu> findByMenuName(String name);
}