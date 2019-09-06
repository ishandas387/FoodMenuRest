package com.menu.service;

import java.util.List;

import com.menu.dto.AddMenuDTO;
import com.menu.dto.MenuDTO;
import com.menu.dto.ResponseDTO;
import com.menu.exception.EntityAlreadyExistsException;
import com.menu.exception.MenuProjectException;

public interface MenuService {

	public ResponseDTO saveMenu(AddMenuDTO menu) throws MenuProjectException, EntityAlreadyExistsException;
	public List<MenuDTO> getMenu();
}
