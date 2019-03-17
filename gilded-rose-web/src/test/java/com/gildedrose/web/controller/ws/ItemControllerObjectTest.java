package com.gildedrose.web.controller.ws;

import com.gildedrose.core.model.Item;
import com.gildedrose.core.service.ItemService;
import com.gildedrose.web.adapter.ItemAdapter;
import com.gildedrose.web.dto.ItemDTO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ItemControllerObjectTest {
    // TODO  create unit tests for each method and limits using a mocked Item service

    @Mock
    private ItemService itemService;
    @Mock
    private ItemAdapter itemAdapter;

    @InjectMocks
    private ItemController itemController;

    @Before
    public final void setUp() throws Exception {
        // TODO: create a mocked ItemService and add it to the controller
        MockitoAnnotations.initMocks(this);

        List<Item> items = Arrays.asList(
                new Item(1,"+5 Dexterity Vest", 10, 20, "vest"),
                new Item(2,"Aged Brie", 2, 0, "cheese"),
                new Item(3,"Elixir of the Mongoose", 5, 7, "elixir"),
                new Item(4,"Sulfuras, Hand of Ragnaros", 0, 80, "sulfuras"),
                new Item(5,"Sulfuras, Hand of Ragnaros", -1, 80, "sulfuras"),
                new Item(6,"Backstage passes to a TAFKAL80ETC concert", 15, 20, "concert pass"),
                new Item(7,"Backstage passes to a TAFKAL80ETC concert", 10, 49, "concert pass"),
                new Item(8,"Backstage passes to a TAFKAL80ETC concert", 5, 49, "concert pass"),
                new Item(9,"Conjured Mana Cake", 3, 6, "conjured")
        );

        Mockito.when(itemService.getItems()).thenReturn(items);
        Mockito.when(itemService.get(ArgumentMatchers.any())).thenReturn(items);
        Mockito.when(itemService.degrade(Mockito.any(Long.class))).thenReturn(true);
    }

    @Test
    public void getItemsTest(){
        List<ItemDTO> items = itemController.getAll();

        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    @Test
    public void getItemTest(){
        List<ItemDTO> items = itemController.getList(Arrays.asList(1L,2L,4L)).getBody();

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