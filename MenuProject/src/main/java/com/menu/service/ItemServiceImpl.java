package com.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.menu.dto.AddItemDTO;
import com.menu.dto.ItemDTO;
import com.menu.dto.ItemGroupResponseDTO;
import com.menu.dto.StatusCode;
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
			Long startTime = System.currentTimeMillis();
			if (!CollectionUtils.isEmpty(itemRepo.findByName(item.getItemName()))) {
				throw new EntityAlreadyExistsException("Item already exists");
			}
			if (!StringUtils.isEmpty(item.getParentMenu())) {
				if (CollectionUtils.isEmpty(menuRepo.findByMenuNameIgnoreCaseAndIsActive(item.getParentMenu(), Boolean.TRUE))) {
					throw new EntityNotFoundException("Parent menu not found");
				}
			}
			Item itemDO = convertToDO(item);
			LOG.info("Time taken to complete the operation :: {}", System.currentTimeMillis() - startTime);
			return itemRepo.save(itemDO);
		} catch (EntityAlreadyExistsException e) {
			throw e;
		} catch (EntityNotFoundException e) {
			throw e;
		} catch (Exception e) {
			LOG.error("Error while saving " + e.getMessage());
			throw new MenuProjectException(e.getLocalizedMessage());
		}
	}

	public Item convertToDO(AddItemDTO item) {
		Long startTime = System.currentTimeMillis();
		Item itm = new Item();
		itm.setDescription(item.getItemDescription());
		itm.setPrice(item.getItemPriceWithCurrency());
		itm.setName(item.getItemName());
		if (!StringUtils.isEmpty(item.getParentMenu())) {
			List<Menu> findByMenuName = menuRepo.findByMenuNameIgnoreCaseAndIsActive(item.getParentMenu(),
					Boolean.TRUE);
			itm.setParentMenu(findByMenuName.get(0));
		}
		LOG.info("Time taken to complete the conversion in milsecs :: {}", System.currentTimeMillis() - startTime);
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

	@Override
	public ItemGroupResponseDTO getItemDetailsByGroup(String groupByRank, String menu)
			throws EntityNotFoundException, MenuProjectException {
		Long startTime = System.currentTimeMillis();
		ItemGroupResponseDTO resp = new ItemGroupResponseDTO();
		resp.setStatus(StatusCode.SUCESS);

		if (StringUtils.isEmpty(menu)) {
			return resp;
		} else {
			try {
				List<Item> listOfItems = getListOfItemsForAMenuIncludingSubs(menu);

				if (!CollectionUtils.isEmpty(listOfItems)) {
					Map<String, List<Item>> groupedMap = new HashMap<>();
					if ("N".equalsIgnoreCase(groupByRank)) {
						resp.setGroupedBy("PRICE");
						groupedMap = listOfItems.stream().collect(Collectors.groupingBy(w -> w.getPrice()));
					} else {
						resp.setGroupedBy("RATING");
						groupedMap = listOfItems.stream()
								.collect(Collectors.groupingBy(w -> String.valueOf(w.getRating())));
					}
					// type conversion
					Map<String, List<ItemDTO>> itemdtoMap = new HashMap<>();
					groupedMap.forEach((k, v) -> {
						itemdtoMap.put(k, convertToDTO(v));
					});
					resp.setItemdtoMap(itemdtoMap);
					LOG.info("Time taken to complete the grouping :: {}", System.currentTimeMillis() - startTime);
				}

			} catch (Exception e) {
				LOG.info("Exception in group by items :: {}", e.getMessage());
				throw new MenuProjectException(e.getMessage());
			}

		}
		return resp;
	}

	private List<Item> getListOfItemsForAMenuIncludingSubs(String menu) throws EntityNotFoundException {
		List<Menu> findByMenuName = getTheMenu(menu);
		/**
		 * If this is a parent menu, get all the items of parent menu and sub menu as
		 * well and then group it
		 */
		// check if it is a parent menu
		Menu menu2 = findByMenuName.get(0);
		Set<Long> menuIds = new HashSet<>();
		if (StringUtils.isEmpty(menu2.getSubMenuOf())) {
			// get all the submenus
			menuIds = menuRepo.getMenuIdBySubMenuAndFlag(String.valueOf(menu2.getMenuId()), Boolean.TRUE);
		}

		// include the parent
		menuIds.add(menu2.getMenuId());

		List<Item> listOfItems = itemRepo.getItemListBasedOnMenu(menuIds);
		return listOfItems;
	}

	private List<Menu> getTheMenu(String menu) throws EntityNotFoundException {
		// get the menu id
		List<Menu> findByMenuName = menuRepo.findByMenuNameIgnoreCaseAndIsActive(menu, Boolean.TRUE);
		if (CollectionUtils.isEmpty(findByMenuName)) {
			throw new EntityNotFoundException("Menu does not exists!");
		}
		return findByMenuName;
	}

	/**
	 * Given a list of items it gives back a list of item dto(s).
	 * 
	 * @param itemList
	 * @return
	 */
	private List<ItemDTO> convertToDTO(List<Item> itemList) {
		List<ItemDTO> itemDTOList = new ArrayList<>();
		itemList.forEach(item -> {
			ItemDTO dto = new ItemDTO();
			dto.setName(item.getName());
			dto.setPrice(item.getPrice());
			dto.setRating(String.valueOf(item.getRating()));
			dto.setDescription(item.getDescription());
			itemDTOList.add(dto);
		});
		return itemDTOList;

	}

	/**
	 * Get price of all items for a menu including sub-menu
	 */
	@Override
	public Double getPriceOfAllItems(String menu) throws EntityNotFoundException {
		List<Item> listOfItems = getListOfItemsForAMenuIncludingSubs(menu);
		return listOfItems.stream().map(Item::getPrice).map(price -> {
			String[] split = price.split("-");
			return split[0];
		}).mapToDouble(x -> Double.valueOf(x)).sum();
	}

	/**
	 * Get Active submenu for a menu.
	 */
	@Override
	public int getActiveSubMenuCount(String menu) throws EntityNotFoundException {
		List<Menu> findByMenuName = getTheMenu(menu);
		return menuRepo.getMenuIdBySubMenuAndFlag(String.valueOf(findByMenuName.get(0).getMenuId()), Boolean.TRUE)
				.size();
	}

}
