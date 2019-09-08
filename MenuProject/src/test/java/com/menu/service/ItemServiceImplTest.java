/**
 * 
 */
package com.menu.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.menu.dto.AddItemDTO;
import com.menu.dto.ItemGroupResponseDTO;
import com.menu.entity.Item;
import com.menu.entity.Menu;
import com.menu.exception.EntityAlreadyExistsException;
import com.menu.exception.EntityNotFoundException;
import com.menu.repository.ItemRepository;
import com.menu.repository.MenuRepository;

/**
 * @author ishan
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {
	@Mock
	private ItemRepository itemRepo;

	@Mock
	private MenuRepository menuRepo;
	@InjectMocks
	private ItemServiceImpl itemServiceImpl;
	
	private List<Item> listOfMockedItems;
	

	/**
	 * Test method for {@link com.menu.service.ItemServiceImpl#saveItem(com.menu.dto.AddItemDTO)}.
	 */
	@Test
	public void testSaveItem() throws Exception {
		AddItemDTO dto = new AddItemDTO();
		dto.setItemName("testItem");
		Mockito.when(itemRepo.findByName("testItem")).thenReturn(new ArrayList<>());
		Mockito.when(itemRepo.save(Mockito.any())).thenReturn(new Item());
		Item saveItem = itemServiceImpl.saveItem(dto);
		assertNotNull(saveItem);
	}
	
	
	@Test(expected = EntityAlreadyExistsException.class)
	public void testSaveItemForException() throws Exception {
		AddItemDTO dto = new AddItemDTO();
		dto.setItemName("testItem");
		List<Item> list = new ArrayList<>();
		list.add(new Item());
		Mockito.when(itemRepo.findByName("testItem")).thenReturn(list);
		itemServiceImpl.saveItem(dto);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testSaveItemForException2() throws Exception {
		AddItemDTO dto = new AddItemDTO();
		dto.setItemName("testItem");
		dto.setParentMenu("parent");
		List<Menu> list = new ArrayList<>();
		list.add(new Menu());
		Mockito.when(itemRepo.findByName("testItem")).thenReturn(new ArrayList<>());
		Mockito.when(menuRepo.findByMenuNameIgnoreCaseAndIsActive("parent", Boolean.TRUE)).thenReturn((new ArrayList<>()));
		Item saveItem = itemServiceImpl.saveItem(dto);
	}
	

	

	/**
	 * Test method for {@link com.menu.service.ItemServiceImpl#getItemDetailsByGroup(java.lang.String, java.lang.String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetItemDetailsByGroup() throws Exception {
		List<Menu> listOfMenu = new ArrayList<>();
		Menu m1 = new Menu();
		m1.setActive(true);
		m1.setMenuId(1L);
		m1.setMenuName("Italian");
	
		listOfMenu.add(m1 );
		Menu m2 = new Menu();
		m2.setActive(true);
		m2.setMenuId(2L);
		m2.setSubMenuOf("1");
		m2.setMenuName("Menu2");
		listOfMenu.add(m2 );
		
		Set<Long> set = new HashSet<>();
		set.add(2L);
		Mockito.when(menuRepo.getMenuIdBySubMenuAndFlag(String.valueOf(1L), Boolean.TRUE)).thenReturn(set);
		Mockito.when(menuRepo.findByMenuNameIgnoreCaseAndIsActive("Italian", Boolean.TRUE)).thenReturn(listOfMenu);
		List<Item> listOfItems = new ArrayList<>();
		Item item1 = new Item();
		item1.setName("i1");
		item1.setPrice("1-EUR");
		item1.setRating(2);
		listOfItems.add(item1);
		
		Item item2 = new Item();
		item2.setName("i2");
		item2.setRating(2);
		item2.setPrice("1-EUR");
		listOfItems.add(item2);
		

		Item item3 = new Item();
		item3.setName("i3");
		item3.setPrice("2-EUR");
		item3.setRating(3);
		listOfItems.add(item3);
		
		
		Mockito.when(itemRepo.getItemListBasedOnMenu(Mockito.anySetOf(Long.class))).thenReturn(listOfItems);
		ItemGroupResponseDTO response = itemServiceImpl.getItemDetailsByGroup("N", "Italian");
		assertEquals(2, response.getItemdtoMap().get("1-EUR").size());
		assertEquals(1, response.getItemdtoMap().get("2-EUR").size());
	}

	/**
	 * Test method for {@link com.menu.service.ItemServiceImpl#getItemDetailsByGroup(java.lang.String, java.lang.String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetItemDetailsByGroupForRating() throws Exception {
		List<Menu> listOfMenu = new ArrayList<>();
		Menu m1 = new Menu();
		m1.setActive(true);
		m1.setMenuId(1L);
		m1.setMenuName("Italian");
	
		listOfMenu.add(m1 );
		Menu m2 = new Menu();
		m2.setActive(true);
		m2.setMenuId(2L);
		m2.setSubMenuOf("1");
		m2.setMenuName("Menu2");
		listOfMenu.add(m2 );
		
		Set<Long> set = new HashSet<>();
		set.add(2L);
		Mockito.when(menuRepo.getMenuIdBySubMenuAndFlag(String.valueOf(1L), Boolean.TRUE)).thenReturn(set);
		Mockito.when(menuRepo.findByMenuNameIgnoreCaseAndIsActive("Italian", Boolean.TRUE)).thenReturn(listOfMenu);
		List<Item> listOfItems = new ArrayList<>();
		Item item1 = new Item();
		item1.setName("i1");
		item1.setPrice("1-EUR");
		item1.setRating(2);
		listOfItems.add(item1);
		
		Item item2 = new Item();
		item2.setName("i2");
		item2.setRating(2);
		item2.setPrice("1-EUR");
		listOfItems.add(item2);
		

		Item item3 = new Item();
		item3.setName("i3");
		item3.setPrice("2-EUR");
		item3.setRating(3);
		listOfItems.add(item3);
		
		
		Mockito.when(itemRepo.getItemListBasedOnMenu(Mockito.anySetOf(Long.class))).thenReturn(listOfItems);
		ItemGroupResponseDTO response = itemServiceImpl.getItemDetailsByGroup("Y", "Italian");
		assertEquals(2, response.getItemdtoMap().get("2").size());
		assertEquals(1, response.getItemdtoMap().get("3").size());
	}
	/**
	 * Test method for {@link com.menu.service.ItemServiceImpl#getPriceOfAllItems(java.lang.String)}.
	 */
	@Test
	public void testGetPriceOfAllItems() throws Exception {
		List<Menu> listOfMenu = new ArrayList<>();
		Menu m1 = new Menu();
		m1.setActive(true);
		m1.setMenuId(1L);
		m1.setMenuName("Italian");
	
		listOfMenu.add(m1 );
		Menu m2 = new Menu();
		m2.setActive(true);
		m2.setMenuId(2L);
		m2.setSubMenuOf("1");
		m2.setMenuName("Menu2");
		listOfMenu.add(m2 );
		
		Set<Long> set = new HashSet<>();
		set.add(2L);
		Mockito.when(menuRepo.getMenuIdBySubMenuAndFlag(String.valueOf(1L), Boolean.TRUE)).thenReturn(set);
		Mockito.when(menuRepo.findByMenuNameIgnoreCaseAndIsActive("Italian", Boolean.TRUE)).thenReturn(listOfMenu);
		List<Item> listOfItems = new ArrayList<>();
		Item item1 = new Item();
		item1.setName("i1");
		item1.setPrice("1-EUR");
		item1.setRating(2);
		listOfItems.add(item1);
		
		Item item2 = new Item();
		item2.setName("i2");
		item2.setRating(2);
		item2.setPrice("1-EUR");
		listOfItems.add(item2);
		

		Item item3 = new Item();
		item3.setName("i3");
		item3.setPrice("2-EUR");
		item3.setRating(3);
		listOfItems.add(item3);
		
		
		Mockito.when(itemRepo.getItemListBasedOnMenu(Mockito.anySetOf(Long.class))).thenReturn(listOfItems);
		Double price = itemServiceImpl.getPriceOfAllItems("Italian");
		assertEquals(new Double(4), price);
	}

	/**
	 * Test method for {@link com.menu.service.ItemServiceImpl#getActiveSubMenuCount(java.lang.String)}.
	 */
	
	@Test
	
	public void testGetActiveSubMenuCount() throws Exception {
		List<Menu> listOfMenu = new ArrayList<>();
		Menu m1 = new Menu();
		m1.setActive(true);
		m1.setMenuId(1L);
		m1.setMenuName("Italian");
	
		listOfMenu.add(m1 );
		Menu m2 = new Menu();
		m2.setActive(true);
		m2.setMenuId(2L);
		m2.setSubMenuOf("1");
		m2.setMenuName("Menu2");
		listOfMenu.add(m2 );
		
		Set<Long> set = new HashSet<>();
		set.add(2L);
		Mockito.when(menuRepo.getMenuIdBySubMenuAndFlag(String.valueOf(1L), Boolean.TRUE)).thenReturn(set);
		Mockito.when(menuRepo.findByMenuNameIgnoreCaseAndIsActive("Italian", Boolean.TRUE)).thenReturn(listOfMenu);
		List<Item> listOfItems = new ArrayList<>();
		Item item1 = new Item();
		item1.setName("i1");
		item1.setPrice("1-EUR");
		item1.setRating(2);
		listOfItems.add(item1);
		
		Item item2 = new Item();
		item2.setName("i2");
		item2.setRating(2);
		item2.setPrice("1-EUR");
		listOfItems.add(item2);
		

		Item item3 = new Item();
		item3.setName("i3");
		item3.setPrice("2-EUR");
		item3.setRating(3);
		listOfItems.add(item3);
		
		
		Mockito.when(itemRepo.getItemListBasedOnMenu(Mockito.anySetOf(Long.class))).thenReturn(listOfItems);
		assertEquals(1, itemServiceImpl.getActiveSubMenuCount("Italian"));
	}

}
