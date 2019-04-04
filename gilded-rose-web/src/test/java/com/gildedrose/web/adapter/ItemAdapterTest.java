package com.gildedrose.web.adapter;

import com.gildedrose.core.model.type.AgeingMode;
import com.gildedrose.core.model.Item;
import com.gildedrose.web.dto.ItemDTO;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
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
        //MockitoAnnotations.initMocks(this);

        String largeString = "qsqqsdùlkfsùfnzrognzrognzrufnzeofizeùofijzeùofijzeùfijzùeofijzùefijzùefijzeiofjznzez";

        items = Arrays.asList(
                new Item(1,"+5 Dexterity Vest", 10, 20, "vest"),
                new Item(2,"Aged Brie", 2, 0, "cheese"),
                new Item(3,"Elixir of the Mongoose", 5, 7, "elixir"),
                new Item(4,"Sulfuras, Hand of Ragnaros", 0, 80, "sulfuras"),
                new Item(5,"Sulfuras, Hand of Ragnaros", -1, 80, "sulfuras"),
                new Item(6,"Backstage passes to a TAFKAL80ETC concert", 15, 20, "concert pass"),
                new Item(7,"Backstage passes to a TAFKAL80ETC concert", 10, 49, "concert pass"),
                new Item(8,"Backstage passes to a TAFKAL80ETC concert", 5, 49, "concert pass"),
                new Item(9,"Conjured Mana Cake", 3, 6, "conjured"),

                // limit values
                new Item(Long.MAX_VALUE, largeString, Integer.MAX_VALUE, Integer.MAX_VALUE, largeString ),
                new Item(Long.MAX_VALUE, largeString , Integer.MIN_VALUE, Integer.MIN_VALUE, largeString )
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

            assertNotNull(itemDto.tags);
        }
    }

    @Test
    public void toDtoInvalidValuesTest() {

        List<Item> itemList = Arrays.asList(
                new Item(-1,null, -1, -1, null),
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

            assertNotNull(itemDto.tags);
        }

        ItemDTO itemDto = itemAdapter.toDto(null);
        assertNull(itemDto);
    }

    @Test
    public void toModelTest() {
        for (Item itemExpectedValues: items) {
            ItemDTO itemDto = new ItemDTO(itemExpectedValues.id, itemExpectedValues.name, itemExpectedValues.sellIn,
                    itemExpectedValues.quality, itemExpectedValues.type);
            Item item = itemAdapter.toModel(itemDto);

            assertNotNull(item);
            assertEquals(itemExpectedValues.id, item.id);
            assertEquals(itemExpectedValues.name, item.name);
            assertEquals(itemExpectedValues.sellIn, item.sellIn);
            assertEquals(itemExpectedValues.quality, item.quality);

            assertEquals(itemExpectedValues.type, item.type);
            assertEquals(itemExpectedValues.ageingDegree, item.ageingDegree, 2);
            assertEquals(itemExpectedValues.ageingMode, item.ageingMode);
        }

    }

    @Test
    public void toModelInvalidValuesTest() {

        List<String> tags = Arrays.asList("HQ");

        List<ItemDTO> itemList = Arrays.asList(
                new ItemDTO(-1,null, -1, -1, null),
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

            assertEquals(null, item.type);
            assertEquals(01.f, item.ageingDegree, 2);
            assertEquals(AgeingMode.BAD, item.ageingMode);
        }

        Item item = itemAdapter.toModel(null);
        assertNull(item);
    }
}