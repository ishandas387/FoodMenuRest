/**
 * 
 */
package com.menu.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.menu.dto.AddItemDTO;
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
	
	@Before
	public void beforeTest() {
		//set ups if any
	}
	
	@After
	public void afterTest() {
		//clean ups if any
	}

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
		Mockito.when(itemRepo.save(Mockito.any())).thenReturn(new Item());
		Item saveItem = itemServiceImpl.saveItem(dto);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testSaveItemForException2() throws Exception {
		AddItemDTO dto = new AddItemDTO();
		dto.setItemName("testItem");
		List<Menu> list = new ArrayList<>();
		list.add(new Menu());
		Mockito.when(itemRepo.findByName("testItem")).thenReturn(new ArrayList<>());
		Mockito.when(menuRepo.findByMenuNameIgnoreCaseAndIsActive("parent", Boolean.TRUE)).thenReturn(list);
		Mockito.when(itemRepo.save(Mockito.any())).thenReturn(new Item());
		Item saveItem = itemServiceImpl.saveItem(dto);
	}
	/**
	 * Test method for {@link com.menu.service.ItemServiceImpl#convertToDO(com.menu.dto.AddItemDTO)}.
	 */
	@Test
	public void testConvertToDO() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	/**
	 * Test method for {@link com.menu.service.ItemServiceImpl#getItems()}.
	 */
	@Test
	public void testGetItems() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	/**
	 * Test method for {@link com.menu.service.ItemServiceImpl#saveListOfItems(java.util.List)}.
	 */
	@Test
	public void testSaveListOfItems() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	/**
	 * Test method for {@link com.menu.service.ItemServiceImpl#getItemDetailsByGroup(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetItemDetailsByGroup() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	/**
	 * Test method for {@link com.menu.service.ItemServiceImpl#getPriceOfAllItems(java.lang.String)}.
	 */
	@Test
	public void testGetPriceOfAllItems() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	/**
	 * Test method for {@link com.menu.service.ItemServiceImpl#getActiveSubMenuCount(java.lang.String)}.
	 */
	@Test
	public void testGetActiveSubMenuCount() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}
