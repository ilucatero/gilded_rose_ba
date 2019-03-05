package com.guildedrose.core;

import com.guildedrose.core.model.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class GildedRoseTest {
    @Test
    public void updateQualityTest(){
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                // the following items do not work properly yet
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Conjured Mana Cake", 3, 6) };

        int days = 20;
        for (int i=0; i < days; i++ ) {
            GildedRose.updateQuality(items);
        }

        assertTrue(items[0].sellIn==-10 && items[0].quality==0);
        assertTrue(items[1].sellIn==-18 && items[1].quality==38);
        assertTrue(items[2].sellIn==-15 && items[2].quality==0);
        assertTrue(items[3].sellIn==-20 && items[3].quality==40);
        assertTrue(items[4].sellIn==-21 && items[4].quality==40);
        assertTrue(items[5].sellIn==-5 && items[5].quality==0);
        assertTrue(items[6].sellIn==-10 && items[6].quality==19);
        assertTrue(items[7].sellIn==-15 && items[7].quality==14);
        assertTrue(items[8].sellIn==-17 && items[8].quality==0);
    }
}