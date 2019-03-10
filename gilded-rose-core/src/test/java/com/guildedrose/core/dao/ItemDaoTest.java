package com.guildedrose.core.dao;

import com.guildedrose.core.model.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ItemDaoTest {
    // TODO: modify unit tests for each method and limits using mocked db(jdbc)

    private ItemDao itemDao;

    @Before
    public final void setUp() throws Exception {
        // TODO: assign a mocked ItemDao
        itemDao = new ItemDao();
    }

    @Test
    public final void getAllTest(){
        List<Item> items = itemDao.getAll();

        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    @Test
    public final void getOneTest(){
        // TODO: complete test and implement functionality if required

        Optional<Item> items = itemDao.get(1);

        assertNotNull(items);
        assertTrue(items.isPresent());
    }

    @Test
    public final void getOneLimitsTest(){
        // TODO: complete test and implement functionality if required
        // TODO: test case id doesn't exist (-1, Long.MAX) or null values
    }

    @Test
    public final void getUpdateTest(){
        // TODO: complete test and implement functionality if required

        Item oldItem = itemDao.get(9).get();

        Item item =  new Item(9,"Conjured Mana Cake", 3, 6);
        item.quality = 3;

        String itemStr = item.toString();
        itemDao.update(item, new String[]{"quality"});

        Item newItem = itemDao.get(9).get();

        // check the submitted item was not modified
        assertNotNull(item);
        assertEquals(itemStr, item.toString());

        // check that only quantity value have changed
        assertEquals(newItem.name, oldItem.name);
        assertEquals(newItem.sellIn, oldItem.sellIn);
        assertEquals(newItem.id, oldItem.id);
        // FIXME: since the db is a list, the updated item is always the same. Uncomment and make it work once the db resource is ready
        // Assert.assertNotEquals(oldItem.quality, newItem.quality);
    }

    @Test
    public final void getUpdateLimitsTest(){
        // TODO: complete test and implement functionality if required
        // TODO: test limit cases, e.g. the id or certain param doesn't exist (-1, Long.MAX) or null values
    }

    @Test
    public final void getSaveTest(){
        // TODO: complete test and implement functionality if required
    }

    @Test
    public final void getSaveLimitsTest(){
        // TODO: complete test and implement functionality if required
        // TODO: test limit case no names, or null values
    }

    @Test
    public final void getDeleteTest(){
        // TODO: complete test and implement functionality if required
    }

    @Test
    public final void getDeleteLimitTest(){
        // TODO: complete test and implement functionality if required
        // TODO: test limit case no names, or null values
    }
}