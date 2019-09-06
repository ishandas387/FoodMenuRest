package com.menu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.menu.dto.AddItemDTO;
import com.menu.entity.Item;
import com.menu.entity.Menu;
import com.menu.exception.EntityAlreadyExistsException;
import com.menu.exception.EntityNotFoundException;
import com.menu.exception.MenuProjectException;
import com.menu.repository.ItemRepository;
import com.menu.repository.MenuRepository;

@Service
public class ItemServiceImpl implements ItemService {

	private static final Logger LOG = LoggerFactory.getLogger(ItemServiceImpl.class);
	@Autowired
	ItemRepository itemRepo;
	@Autowired
	MenuRepository menuRepo;

	/**
	 * Adding item to ITEM
	 */
	@Override
	public Item saveItem(AddItemDTO item)
			throws MenuProjectException, EntityAlreadyExistsException, EntityNotFoundException {
		try {

			if (!CollectionUtils.isEmpty(itemRepo.findByName(item.getItemName()))) {
				throw new EntityAlreadyExistsException("Item already exists");
			}
			if (!StringUtils.isEmpty(item.getParentMenu())) {
				if (null == menuRepo.findByMenuName(item.getParentMenu())) {
					throw new EntityNotFoundException("Parent menu not found");
				}
			}
			Item itemDO = convertToDO(item);
			return itemRepo.save(itemDO);
		} catch (Exception e) {
			LOG.error("Error while saving " + e.getMessage());
			throw new MenuProjectException(e.getLocalizedMessage());
		}
	}

	public Item convertToDO(AddItemDTO item) {
		Item itm = new Item();
		itm.setDescription(item.getItemDescription());
		itm.setPrice(item.getItemPriceWithCurrency());
		itm.setName(item.getItemName());
		if (!StringUtils.isEmpty(item.getParentMenu())) {
			List<Menu> findByMenuName = menuRepo.findByMenuName(item.getParentMenu());
			itm.setParentMenu(findByMenuName.get(0));
		}
		return itm;

	}

	@Override
	public List<Item> getItems() {
		return (List<Item>) itemRepo.findAll();
	}

	@Override
	public int saveListOfItems(List<AddItemDTO> listOfDTOs) {
	
		List<Item> items = listOfDTOs.stream().map(itemDto -> convertToDO(itemDto)).collect(Collectors.toList());
		return ((List<Item>) itemRepo.saveAll(items)).size();
	}

	
}
