package com.menu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.menu.dto.AddMenuDTO;
import com.menu.dto.MenuDTO;
import com.menu.dto.ResponseDTO;
import com.menu.exception.EntityAlreadyExistsException;
import com.menu.exception.MenuProjectException;
import com.menu.service.MenuService;
/**
 * Controller for menu manipulation.
 * @author ishan
 *
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

	//Menu service
	private final MenuService menuService;

	@Autowired
	public MenuController(final MenuService menuService) {
		this.menuService = menuService;
	}

	/**
	 * Creating a menu
	 * @param menu
	 * @return
	 * @throws MenuProjectException
	 * @throws EntityAlreadyExistsException
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public  ResponseDTO createMenu(@Valid @RequestBody AddMenuDTO menu) throws MenuProjectException, EntityAlreadyExistsException  {
		return menuService.saveMenu(menu);
	}
	/**
	 * Getting all menu with sub menus and items.
	 * @return
	 */
	@GetMapping
	public List<MenuDTO> getMenu(){
		return menuService.getMenu();
	}
	@GetMapping("/{id}")
	public List<MenuDTO> getMenuById(){
		return menuService.getMenu();
	}
	
	@DeleteMapping("/{id}")
	public void deleteByMenuId(@PathVariable("id") Long menuId) {
		 menuService.deleteMenu(menuId);
	}
	
 
}
