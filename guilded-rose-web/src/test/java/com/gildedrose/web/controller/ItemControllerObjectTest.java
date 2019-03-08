package com.gildedrose.web.controller;

import com.guildedrose.core.model.Item;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ItemControllerObjectTest {
    // TODO  create unit tests for each method and limits using a mocked Item service

    private ItemController itemController;

    @Before
    public final void setUp() throws Exception {
        // TODO: create a mocked ItemService and add it to the controller
        itemController = new ItemController();
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