package com.menu.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.menu.entity.Menu;

public interface MenuRepository extends CrudRepository<Menu, Long> {

	public List<Menu> findByIsActive(boolean flag);
	public List<Menu> findByMenuNameIgnoreCaseAndIsActive(String name,boolean isActive);
	@Query("select m.menuId from Menu m where m.subMenuOf =:id and m.isActive =:flag ")
	public Set<Long> getMenuIdBySubMenuAndFlag(@Param("id") String id, @Param("flag") Boolean flag);
	
}