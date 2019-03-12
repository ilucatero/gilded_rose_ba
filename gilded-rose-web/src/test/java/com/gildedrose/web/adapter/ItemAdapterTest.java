package com.gildedrose.web.adapter;

import com.gildedrose.core.model.Item;
import com.gildedrose.core.service.ItemService;
import com.gildedrose.web.controller.ws.ItemController;
import com.gildedrose.web.dto.ItemDTO;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration({ "/spring-bean-web-conf-test.xml" })
@ComponentScan("com.gildedrose.web")
@RunWith(SpringJUnit4ClassRunner.class)
public class ItemAdapterTest {

    @Autowired
    private ItemAdapter itemAdapter;

    private List<Item> items;

    @Before
    public final void setUp() throws Exception {
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
                new Item(9,"Conjured Mana Cake", 3, 6),

                // limit values
                new Item(Long.MAX_VALUE,"               ", Integer.MAX_VALUE, Integer.MAX_VALUE),
                new Item(Long.MAX_VALUE,"               ", Integer.MIN_VALUE, Integer.MIN_VALUE)
        );

    }

    @Test
    public void toDtoTest() {
        for (Item itemExpectedValues : items) {
            ItemDTO itemDto = itemAdapter.toDto(itemExpectedValues);

            assertNotNull(itemDto);
            assertThat(itemDto.name, Matchers.not(Matchers.isEmptyOrNullString()));
            assertEquals(itemExpectedValues.id, itemDto.id);
            assertEquals(itemExpectedValues.name, itemDto.name);
            assertEquals(itemExpectedValues.sellIn, itemDto.sellIn);
            assertEquals(itemExpectedValues.quality, itemDto.quality);

            assertNull(itemDto.tags);
        }
    }

    @Test
    public void toDtoInvalidValuesTest() {

        List<Item> itemList = Arrays.asList(
                new Item(-1,null, -1, -1),
                new Item()
        );


        for (Item itemExpectedValues : itemList) {

            ItemDTO itemDto = itemAdapter.toDto(itemExpectedValues);

            assertNotNull(itemDto);
            assertNull(itemDto.name);
            assertEquals(itemExpectedValues.id, itemDto.id);
            assertEquals(itemExpectedValues.name, itemDto.name);
            assertEquals(itemExpectedValues.sellIn, itemDto.sellIn);
            assertEquals(itemExpectedValues.quality, itemDto.quality);

            assertNull(itemDto.tags);
        }

        ItemDTO itemDto = itemAdapter.toDto(null);
        assertNull(itemDto);
    }

    @Test
    public void toModelTest() {
        for (Item itemExpectedValues: items) {
            ItemDTO itemDto = new ItemDTO(itemExpectedValues.id, itemExpectedValues.name, itemExpectedValues.sellIn, itemExpectedValues.quality);
            Item item = itemAdapter.toModel(itemDto);

            assertNotNull(item);
            assertEquals(itemExpectedValues.id, item.id);
            assertEquals(itemExpectedValues.name, item.name);
            assertEquals(itemExpectedValues.sellIn, item.sellIn);
            assertEquals(itemExpectedValues.quality, item.quality);
        }

    }

    @Test
    public void toModelInvalidValuesTest() {

        List<String> tags = Arrays.asList("HQ");

        List<ItemDTO> itemList = Arrays.asList(
                new ItemDTO(-1,null, -1, -1),
                new ItemDTO()
        );

        for (ItemDTO itemExpectedValues: itemList) {
            itemExpectedValues.tags = tags;

            Item item = itemAdapter.toModel(itemExpectedValues);

            assertNotNull(item);
            assertNull(item.name);
            assertEquals(itemExpectedValues.id, item.id);
            assertEquals(itemExpectedValues.name, item.name);
            assertEquals(itemExpectedValues.sellIn, item.sellIn);
            assertEquals(itemExpectedValues.quality, item.quality);

        }

        Item item = itemAdapter.toModel(null);
        assertNull(item);
    }
}