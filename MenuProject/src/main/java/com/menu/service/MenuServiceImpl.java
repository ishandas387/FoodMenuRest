package com.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.menu.dto.AddMenuDTO;
import com.menu.dto.ItemDTO;
import com.menu.dto.MenuDTO;
import com.menu.dto.ResponseDTO;
import com.menu.dto.StatusCode;
import com.menu.entity.Item;
import com.menu.entity.Menu;
import com.menu.exception.EntityAlreadyExistsException;
import com.menu.exception.MenuProjectException;
import com.menu.repository.ItemRepository;
import com.menu.repository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService {

	private static final Logger LOG = LoggerFactory.getLogger(MenuServiceImpl.class);
	@Autowired
	MenuRepository menuRepo;
	@Autowired
	ItemRepository itemRepo;
	@Autowired
	ItemServiceImpl itemService;

	/**
	 * Getting the full menu list which are active, with sub menu and items.
	 */
	@Override
	public List<MenuDTO> getMenu() {
		Long timeStart = System.currentTimeMillis();
		//gets active menu list
		List<Menu> listOfMenu = (List<Menu>) menuRepo.findByIsActive(Boolean.TRUE);
		List<MenuDTO> listMenuDTO = new ArrayList<>();
		//get the primary menu items.
		List<Menu> primaryMenu = listOfMenu.stream().filter(m -> m.getSubMenu() == null || m.getSubMenu().isEmpty())
				.collect(Collectors.toList());
		//create dto for primary
		listMenuDTO.addAll(getListOfSubMenuDTO(primaryMenu));
		/**
		 * creating a hashmap for lookup.
		 * The map will have submenuOf ID i.e the primary menu ID and the list
		 * of sub menu objects.
		 * 
		 */
		Map<String, List<Menu>> mapOfSubMenu = new HashMap<>();
		for (Menu m : listOfMenu) {
			if (!StringUtils.isEmpty(m.getSubMenu())) {
				if (!mapOfSubMenu.containsKey(m.getSubMenu())) {
					List<Menu> subMenus = new ArrayList<Menu>();
					subMenus.add(m);
					mapOfSubMenu.put(m.getSubMenu(), subMenus);
				} else {
					mapOfSubMenu.get(m.getSubMenu()).add(m);
				}
			}
		}
		listMenuDTO.forEach(m -> {

			if (mapOfSubMenu.containsKey(String.valueOf(m.getId()))) {
				m.setSubMenu(getListOfSubMenuDTO(mapOfSubMenu.get(String.valueOf(m.getId()))));
			}
		});
		LOG.info("Time to complete procedure call {}",System.currentTimeMillis() -timeStart);
		return listMenuDTO;
	}

	/**
	 * Returns list of menu dto
	 * @param list
	 * @return
	 */
	private List<MenuDTO> getListOfSubMenuDTO(List<Menu> list) {
		List<MenuDTO> list2 = new ArrayList<>();
		list.forEach(menu -> {
			MenuDTO m = new MenuDTO();
			m.setName(menu.getMenuName());
			m.setDescription(menu.getMenuDescription());
			m.setItems(getItems(menu.getListOfItems()));
			m.setId(menu.getMenuId());
			list2.add(m);
		});
		return list2;
	}

	/**
	 * Returns list of items dto
	 * @param listOfItems
	 * @return
	 */
	private List<ItemDTO> getItems(List<Item> listOfItems) {
		List<ItemDTO> listOfItemsDTO = new ArrayList<>();
		listOfItems.forEach(item -> {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setDescription(item.getDescription());
			itemDTO.setName(item.getName());
			itemDTO.setPrice(item.getPrice());
			itemDTO.setRating(item.getRating() != null ? item.getRating().toString() : null);
			listOfItemsDTO.add(itemDTO);
		});
		return listOfItemsDTO;
	}

	/**
	 * saving the menu and respective items for it.
	 */
	@Override
	@Transactional
	public ResponseDTO saveMenu(AddMenuDTO menu) throws MenuProjectException, EntityAlreadyExistsException {
		try {
			Long timeStart = System.currentTimeMillis();
			ResponseDTO resp = new ResponseDTO();
			resp.setStatus(StatusCode.FAILURE);
			if (!CollectionUtils.isEmpty(menuRepo.findByMenuName(menu.getMenuName()))) {
				throw new EntityAlreadyExistsException("Menu Name already in use");
			}
			Menu menuEntity = new Menu();
			menuEntity.setMenuDescription(menu.getMenuDescription());
			menuEntity.setMenuName(menu.getMenuName());
			menuEntity.setActive(Boolean.TRUE);
			if(!StringUtils.isEmpty(menu.getParentMenu())) {
				List<Menu> findByMenuName = menuRepo.findByMenuName(menu.getParentMenu());
				if(!findByMenuName.isEmpty()) {
					menuEntity.setSubMenu(String.valueOf(findByMenuName.get(0).getMenuId()));
				}
			}
			if (!CollectionUtils.isEmpty(menu.getListOfItems())) {
				List<Item> items = menu.getListOfItems().stream().map(item -> itemService.convertToDO(item))
						.collect(Collectors.toList());
				menuEntity.setListOfItems(items);
				items.forEach(item -> item.setParentMenu(menuEntity));
			}
			if(null != menuRepo.save(menuEntity)) {
				resp.setStatus(StatusCode.SUCESS);
			}
			LOG.info("Time to complete save call {}",System.currentTimeMillis() -timeStart);
			return resp;
		} catch (Exception e) {
			throw new MenuProjectException(e.getLocalizedMessage());
		}
	}

}
