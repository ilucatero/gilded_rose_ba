package com.gildedrose.web.controller;

import com.guildedrose.core.dao.Dao;
import com.guildedrose.core.model.Item;
import com.guildedrose.core.service.ItemService;
import com.guildedrose.core.service.QualityManagerService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ItemControllerObjectTest {
    // TODO  create unit tests for each method and limits using a mocked Item service

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    @Before
    public final void setUp() throws Exception {
        // TODO: create a mocked ItemService and add it to the controller
        MockitoAnnotations.initMocks(this);

        List<Item> items = Arrays.asList(
                new Item(1,"+5 Dexterity Vest", 10, 20),
                new Item(2,"Aged Brie", 2, 0),
                new Item(3,"Elixir of the Mongoose", 5, 7),
                new Item(4,"Sulfuras, Hand of Ragnaros", 0, 80),
                new Item(5,"Sulfuras, Hand of Ragnaros", -1, 80),
                new Item(6,"Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item(7,"Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item(8,"Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item(9,"Conjured Mana Cake", 3, 6)
        );

        Mockito.when(itemService.getItems()).thenReturn(items);
        Mockito.when(itemService.degrade(Mockito.any(Long.class))).thenReturn(true);
    }

    @Test
    public void getItemsTest(){
        List<Item> items = itemController.getItems();

        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    @Test
    @Ignore // TODO remove this line and make the test pass by creating the functionality
    public void degradeItemSuccessTest(){
        Boolean isUpdated = itemController.degrade(9L);

        assertNotNull(isUpdated );
        assertTrue(isUpdated );
    }

    @Test
    @Ignore // TODO remove this line and make the test pass by creating the functionality
    public void degradeItemFailTest(){

        Boolean isUpdated = itemController.degrade(null);
        assertNotNull(isUpdated );
        assertFalse(isUpdated );

        // test with id < 0
        isUpdated = itemController.degrade(-1L);
        assertNotNull(isUpdated );
        assertFalse(isUpdated );

        // test with non existing id
        isUpdated = itemController.degrade(Long.MAX_VALUE);
        assertNotNull(isUpdated );
        assertFalse(isUpdated );
    }
}