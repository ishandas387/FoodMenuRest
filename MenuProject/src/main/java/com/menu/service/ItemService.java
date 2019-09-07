package com.menu.service;

import java.util.List;

import com.menu.dto.AddItemDTO;
import com.menu.dto.ItemGroupResponseDTO;
import com.menu.entity.Item;
import com.menu.exception.EntityAlreadyExistsException;
import com.menu.exception.EntityNotFoundException;
import com.menu.exception.MenuProjectException;

public interface ItemService {

	public Item saveItem(AddItemDTO menu) throws MenuProjectException, EntityAlreadyExistsException, EntityNotFoundException;
	public List<Item> getItems();
	public int saveListOfItems(List<AddItemDTO> listOfDTOs);
	public ItemGroupResponseDTO getItemDetailsByGroup(String groupByRank, String menu) throws EntityNotFoundException, MenuProjectException;
	public Double getPriceOfAllItems(String menu) throws EntityNotFoundException;
	public int getActiveSubMenuCount(String menu) throws EntityNotFoundException;
}
