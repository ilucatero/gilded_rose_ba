package com.gildedrose.core.service;

import com.gildedrose.core.dao.Dao;
import com.gildedrose.core.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

public class ItemServiceTest {
    // TODO  create unit tests for each method and limits using a mocked Item Dao

    @Mock
    private Dao<Item> itemDao;
    @Mock
    QualityManagerService qualityManagerService;

    @InjectMocks
    private ItemService itemService;

    private List<Item> items;

    @Before
    public final void setUp() throws Exception {
        // TODO: create a mocked ItemDao and add it to the service
        MockitoAnnotations.initMocks(this);

        items = Arrays.asList(
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

    }

    @Test
    public void getItemsTest(){
        when(itemDao.getAll()).thenReturn(items);

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