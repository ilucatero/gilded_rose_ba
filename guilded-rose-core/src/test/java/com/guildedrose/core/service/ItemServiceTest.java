package com.guildedrose.core.service;

import com.guildedrose.core.model.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ItemServiceTest {
    // TODO  create unit tests for each method and limits using a mocked Item Dao

    private ItemService itemService;

    @Before
    public final void setUp() throws Exception {
        // TODO: create a mocked ItemDao and add it to the service
        itemService = new ItemService();
    }

    @Test
    public void getItemsTest(){
        List<Item> items = itemService.getItems();

        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    @Test
    public void markBestQualityItemsTest(){
        // TODO: complete test and implement functionality if required
    }

    @Test
    public void updateItemTest(){
        // TODO: complete test and implement functionality if required

    }



}