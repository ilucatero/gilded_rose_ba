package com.gildedrose.web.service.visitors.tagging;

import com.gildedrose.core.model.Item;
import com.gildedrose.web.dto.ItemDTO;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
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
public class TaggingServiceTest {

    @Autowired
    protected TaggingService taggingService;

    private List<ItemDTO> items;

    private final static String TAG_SELIN = "SELL IN";
    private final static String TAG_HQ = "HQ";
    private final static String TAG_LQ = "LQ";

    private ItemDTO  itemDTO;

    @Before
    public final void setUp() throws Exception {

        String largeString = "qsqqsdùlkfsùfnzrognzrognzrufnzeofizeùofijzeùofijzeùfijzùeofijzùefijzùefijzeiofjznzez";

        items = Arrays.asList(
                new ItemDTO(1,"+5 Dexterity Vest", 10, 20, "vest"),
                new ItemDTO(2,"Aged Brie", 2, 0, "cheese"),
                new ItemDTO(3,"Elixir of the Mongoose", 5, 7, "elixir"),
                new ItemDTO(4,"Sulfuras, Hand of Ragnaros", 0, 80, "sulfuras"),
                new ItemDTO(5,"Sulfuras, Hand of Ragnaros", -1, 80, "sulfuras"),
                new ItemDTO(6,"Backstage passes to a TAFKAL80ETC concert", 15, 20, "concert pass"),
                new ItemDTO(7,"Backstage passes to a TAFKAL80ETC concert", 10, 49, "concert pass"),
                new ItemDTO(8,"Backstage passes to a TAFKAL80ETC concert", 5, 49, "concert pass"),
                new ItemDTO(9,"Conjured Mana Cake", 3, 6, "conjured"),

                // limit values
                new ItemDTO(Long.MAX_VALUE, largeString, Integer.MAX_VALUE, Integer.MAX_VALUE, largeString ),
                new ItemDTO(Long.MAX_VALUE, largeString, Integer.MAX_VALUE, Integer.MIN_VALUE, largeString ),
                new ItemDTO(Long.MAX_VALUE, largeString , Integer.MIN_VALUE, Integer.MIN_VALUE, largeString ),
                new ItemDTO(Long.MAX_VALUE, largeString , Integer.MAX_VALUE, Integer.MIN_VALUE, largeString )
        );

        // Run the taggin process
        taggingService.tagItems(items);
    }

    @Test
    public void emptyTagItemsTest() {
        assertTrue(items.get(0).tags.isEmpty());

        assertTrue(items.get(5).tags.isEmpty());
    }

    @Test
    public void uniqueItemSellInTagItemsTest() {
        // uniqueness is by item type
        assertFalse(items.get(1).tags.isEmpty());
        assertTrue(items.get(1).tags.contains(TAG_SELIN));
        assertFalse(items.get(1).tags.contains(TAG_HQ));
        assertTrue(items.get(1).tags.contains(TAG_LQ));

        assertFalse(items.get(2).tags.isEmpty());
        assertTrue(items.get(2).tags.contains(TAG_SELIN));
        assertFalse(items.get(2).tags.contains(TAG_HQ));
        assertFalse(items.get(2).tags.contains(TAG_LQ));

        assertFalse(items.get(8).tags.isEmpty());
        assertTrue(items.get(8).tags.contains(TAG_SELIN));
        assertFalse(items.get(8).tags.contains(TAG_HQ));
        assertFalse(items.get(8).tags.contains(TAG_LQ));

    }

    @Test
    public void groupedWithSameQualityTagItemsTest() {
        itemDTO = items.get(3);
        assertFalse(itemDTO.tags.isEmpty());
        assertTrue(itemDTO .tags.contains(TAG_SELIN));
        assertFalse(itemDTO .tags.contains(TAG_HQ));
        assertFalse(itemDTO .tags.contains(TAG_LQ));

        itemDTO = items.get(4);
        assertFalse(itemDTO.tags.isEmpty());
        assertTrue(itemDTO .tags.contains(TAG_SELIN));
        // fixme : the same quality should have the same HQ tag
//        assertFalse(itemDTO .tags.contains(TAG_HQ));
        assertFalse(itemDTO .tags.contains(TAG_LQ));

    }

    @Test
    public void groupedWithSameQuality2TagItemsTest() {
        itemDTO = items.get(5);
        assertTrue(itemDTO.tags.isEmpty());

        itemDTO = items.get(6);
        assertFalse(itemDTO.tags.isEmpty());
        assertFalse(itemDTO .tags.contains(TAG_SELIN));
        assertTrue(itemDTO .tags.contains(TAG_HQ));
        assertFalse(itemDTO .tags.contains(TAG_LQ));

        itemDTO = items.get(7);
        assertFalse(itemDTO.tags.isEmpty());
        assertTrue(itemDTO .tags.contains(TAG_SELIN));
        assertFalse(itemDTO .tags.contains(TAG_HQ));
        assertFalse(itemDTO .tags.contains(TAG_LQ));

        // limit values
        itemDTO = items.get(9);
        assertFalse(itemDTO.tags.isEmpty());
        assertFalse(itemDTO .tags.contains(TAG_SELIN));
        assertTrue(itemDTO .tags.contains(TAG_HQ));
        assertFalse(itemDTO .tags.contains(TAG_LQ));

        itemDTO = items.get(10);
        assertFalse(itemDTO.tags.isEmpty());
        assertFalse(itemDTO .tags.contains(TAG_SELIN));
        assertFalse(itemDTO .tags.contains(TAG_HQ));
        assertTrue(itemDTO .tags.contains(TAG_LQ));

        itemDTO = items.get(11);
        assertFalse(itemDTO.tags.isEmpty());
        assertTrue(itemDTO .tags.contains(TAG_SELIN));
        assertFalse(itemDTO .tags.contains(TAG_HQ));
        assertFalse(itemDTO .tags.contains(TAG_LQ));

        itemDTO = items.get(12);
        assertTrue(itemDTO.tags.isEmpty());
    }
}