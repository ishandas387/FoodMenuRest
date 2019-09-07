package com.menu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.menu.dto.AddItemDTO;
import com.menu.dto.ItemGroupResponseDTO;
import com.menu.entity.Item;
import com.menu.exception.EntityAlreadyExistsException;
import com.menu.exception.EntityNotFoundException;
import com.menu.exception.MenuProjectException;
import com.menu.service.ItemService;
/**
 * Controller for item manipulation.
 * @author ishan
 *
 */
@RestController
@RequestMapping("/item")
public class ItemController {

	//Item service
	private final ItemService itemService;

	@Autowired
	public ItemController(final ItemService itemService) {
		this.itemService = itemService;
	}


	/**
	 * Adding a standalone item or item with parent menu name. 
	 * @param item
	 * @return
	 * @throws MenuProjectException
	 * @throws EntityAlreadyExistsException
	 * @throws EntityNotFoundException 
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Item createItem(@Valid @RequestBody AddItemDTO item) throws MenuProjectException, EntityAlreadyExistsException, EntityNotFoundException  {
		return itemService.saveItem(item);
	}
	/**
	 * Getting all items with sub menus and items.
	 * Flag groupby-rank header if set to Y the results are grouped by rating
	 * @return
	 * @throws EntityNotFoundException 
	 * @throws MenuProjectException 
	 */
	@GetMapping("/group")
	public ItemGroupResponseDTO groupBy(@RequestHeader(name="groupby-rank",defaultValue = "N") String groupByRank, @RequestHeader("menu") String menu) throws EntityNotFoundException, MenuProjectException{
		return itemService.getItemDetailsByGroup(groupByRank, menu);
	}

	@GetMapping("/allprice")
	public Double getPriceOfAllItems(@RequestHeader("menu") String menu) throws EntityNotFoundException, MenuProjectException{
		return itemService.getPriceOfAllItems( menu);
	}

	@GetMapping("/active-submenu")
	public int getNumberOfActiveSubMenu(@RequestHeader("menu") String menu) throws EntityNotFoundException, MenuProjectException{
		return itemService.getActiveSubMenuCount( menu);
	}
}
