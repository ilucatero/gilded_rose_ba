package com.guildedrose.core.service;

import com.guildedrose.core.model.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class QualityManagerServiceTest {

    // TODO: the below test marked as fixme must be adapted to follow the new requirements (README)

    /**
     * Local method to run the updateQuality x number of times
     * @param items the items to update
     * @param days the number of times to run
     */
    private void processQualityForDays(Item[] items, int days){
        for (int i=0; i < days; i++ ) {
            QualityManagerService.updateQuality(items);
        }
    }

    @Test
    public void updateQualityBasicTest(){
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
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
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
        };

        processQualityForDays(items, 20);

        assertTrue(items[0].sellIn==-20 && items[0].quality==40);
        assertTrue(items[1].sellIn==-21 && items[1].quality==40);
    }

    // FIXME: it does not work as required
    @Test
    public void updateQualityBackstagePassesTest(){
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
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
                new Item("Conjured Mana Cake", 3, 6),
        };

        processQualityForDays(items, 20);

        assertTrue(items[0].sellIn==-17 && items[0].quality==0);
    }
}