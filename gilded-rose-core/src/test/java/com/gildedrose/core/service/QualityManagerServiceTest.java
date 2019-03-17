package com.gildedrose.core.service;

import com.gildedrose.core.model.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class QualityManagerServiceTest {

    // TODO: the below test marked as fixme must be adapted to follow the new requirements (README)

    private QualityManagerService qualityManagerService = new QualityManagerService();

    /**
     * Local method to run the updateQuality x number of times
     * @param items the items to update
     * @param days the number of times to run
     */
    private void processQualityForDays(Item[] items, int days){
        for (int i=0; i < days; i++ ) {
            qualityManagerService.updateQuality(items);
        }
    }

    @Test
    public void updateQualityBasicTest(){
        Item[] items = new Item[] {
                new Item(1,"+5 Dexterity Vest", 10, 20, "vest"),
                new Item(2,"Aged Brie", 2, 0, "cheese"),
                new Item(3,"Elixir of the Mongoose", 5, 7,"elixir"),
        };

        processQualityForDays(items, 20);

        assertTrue(items[0].sellIn==-10 && items[0].quality==0);
        assertTrue(items[1].sellIn==-18 && items[1].quality==38);
        assertTrue(items[2].sellIn==-15 && items[2].quality==0);
    }

    // FIXME: it does not work as required
    @Test
    public void updateQualitySulfurasTest(){
        Item[] items = new Item[] {
                new Item(1,"Sulfuras, Hand of Ragnaros", 0, 80, "sulfuras"),
                new Item(2,"Sulfuras, Hand of Ragnaros", -1, 80, "sulfuras"),
        };

        processQualityForDays(items, 20);

        assertTrue(items[0].sellIn==-20 && items[0].quality==40);
        assertTrue(items[1].sellIn==-21 && items[1].quality==40);
    }

    // FIXME: it does not work as required
    @Test
    public void updateQualityBackstagePassesTest(){
        Item[] items = new Item[] {
                new Item(1,"Backstage passes to a TAFKAL80ETC concert", 15, 20, "concert pass"),
                new Item(2,"Backstage passes to a TAFKAL80ETC concert", 10, 49, "concert pass"),
                new Item(3,"Backstage passes to a TAFKAL80ETC concert", 5, 49, "concert pass"),
        };

        processQualityForDays(items, 20);

        assertTrue(items[0].sellIn==-5 && items[0].quality==0);
        assertTrue(items[1].sellIn==-10 && items[1].quality==19);
        assertTrue(items[2].sellIn==-15 && items[2].quality==14);
    }

    // FIXME: it does not work as required
    @Test
    public void updateQualityConjuredTest(){
        Item[] items = new Item[] {
                new Item(1,"Conjured Mana Cake", 3, 6, "conjured"),
        };

        processQualityForDays(items, 20);

        assertTrue(items[0].sellIn==-17 && items[0].quality==0);
    }
}