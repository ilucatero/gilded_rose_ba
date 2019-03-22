package com.gildedrose.core.service;

import com.gildedrose.core.model.Item;
import com.gildedrose.core.model.type.AgeingMode;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Arrays;

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
            qualityManagerService.updateQuality(Arrays.asList(items));
        }
    }

    @Test
    public void updateQualityBasicBadModeTest(){
        Item[] items = new Item[] {
                new Item(1,"+5 Dexterity Vest", 10, 20, "vest"),
                new Item(2,"Aged Brie", 2, 0, "cheese", 1, AgeingMode.BAD),
                new Item(3,"Elixir of the Mongoose", 5, 7,"elixir",1, AgeingMode.BAD),
        };

        processQualityForDays(items, 20);

        assertTrue(items[0].sellIn==-10 && items[0].quality==0);
        assertTrue(items[1].sellIn==-18 && items[1].quality==0);
        assertTrue(items[2].sellIn==-15 && items[2].quality==0);
    }

    @Test // FIXME: it doesnt wor as required
    public void updateQualityBasicBadModeAgeingDegreeTest(){
        Item[] items = new Item[] {
                new Item(1,"+5 Dexterity Vest", 10, 20, "vest", 4, AgeingMode.BAD),
                new Item(2,"Aged Brie", 2, 0, "cheese", 4, AgeingMode.BAD),
                new Item(3,"Elixir of the Mongoose", 5, 7,"elixir",4, AgeingMode.BAD),
        };

        processQualityForDays(items, 2);

        assertThat(items[0].sellIn , Is.is(8));
        assertThat(items[0].quality, Is.is(12));

        assertThat(items[1].sellIn , Is.is(0));
        assertThat(items[1].quality, Is.is(0));

        assertThat(items[2].sellIn , Is.is(3));
        assertThat(items[2].quality, Is.is(0));

        processQualityForDays(items, 18);

        assertTrue(items[0].sellIn==-10 && items[0].quality==0);
        assertTrue(items[1].sellIn==-18 && items[1].quality==0);
        assertTrue(items[2].sellIn==-15 && items[2].quality==0);
    }

    @Test
    public void updateQualityBasicGoodModeTest(){
        Item[] items = new Item[] {
                new Item(1,"+5 Dexterity Vest", 10, 25, "vest", 1, AgeingMode.GOOD),
                new Item(2,"Aged Brie", 2, 0, "cheese", 1, AgeingMode.GOOD),
                new Item(3,"Elixir of the Mongoose", 5, 7,"elixir",1, AgeingMode.GOOD),
        };

        processQualityForDays(items, 20);

        assertThat(items[0].sellIn , Is.is(-10));
        assertThat(items[0].quality, Is.is(50));

        assertThat(items[1].sellIn , Is.is(-18));
        assertThat(items[1].quality, Is.is(38));

        assertThat(items[2].sellIn , Is.is(-15));
        assertThat(items[2].quality, Is.is(42));
    }

    @Test  // FIXME: it doesnt wor as required
    public void updateQualityBasicGoodModeAgeingDegreeTest(){
        Item[] items = new Item[] {
                new Item(1,"+5 Dexterity Vest", 10, 25, "vest", 4, AgeingMode.GOOD),
                new Item(2,"Aged Brie", 2, 0, "cheese", 4, AgeingMode.GOOD),
                new Item(3,"Elixir of the Mongoose", 5, 7,"elixir",4, AgeingMode.GOOD),
        };
        processQualityForDays(items, 2);

        assertThat(items[0].sellIn , Is.is(8));
        assertThat(items[0].quality, Is.is(33));

        assertThat(items[1].sellIn , Is.is(0));
        assertThat(items[1].quality, Is.is(8));

        assertThat(items[2].sellIn , Is.is(3));
        assertThat(items[2].quality, Is.is(15));

        processQualityForDays(items, 18);

        assertThat(items[0].sellIn , Is.is(-10));
        assertThat(items[0].quality, Is.is(50));

        assertThat(items[1].sellIn , Is.is(-18));
        assertThat(items[1].quality, Is.is(50));

        assertThat(items[2].sellIn , Is.is(-15));
        assertThat(items[2].quality, Is.is(50));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateQualityBasicAgeingDegreeNegativeTest(){
        Item[] items = new Item[] {
                new Item(1,"+5 Dexterity Vest", 10, 20, "vest", -2, AgeingMode.BAD),
                new Item(2,"Aged Brie", 2, 0, "cheese", -2, AgeingMode.BAD),
                new Item(3,"Elixir of the Mongoose", 5, 7,"elixir",-2, AgeingMode.BAD),
        };

        processQualityForDays(items, 20);
    }

    // FIXME: it does not work as required
    @Test
    public void updateQualitySulfurasTest(){
        Item[] items = new Item[] {
                new Item(1,"Sulfuras, Hand of Ragnaros", 0, 80, "sulfuras"),
                new Item(2,"Sulfuras, Hand of Ragnaros", -1, 80, "sulfuras"),
        };

        processQualityForDays(items, 20);

        assertTrue(items[0].sellIn==-0 && items[0].quality==80);
        assertTrue(items[1].sellIn==-1 && items[1].quality==80);
    }

    // FIXME: it does not work as required
    @Test
    public void updateQualityBackstagePassesTest(){
        Item[] items = new Item[] {
                new Item(1,"Backstage passes to a TAFKAL80ETC concert", 15, 20, "concert pass", 1, AgeingMode.GOOD),
                new Item(2,"Backstage passes to a TAFKAL80ETC concert", 10, 49, "concert pass", 1, AgeingMode.GOOD),
                new Item(3,"Backstage passes to a TAFKAL80ETC concert", 5, 49, "concert pass", 1, AgeingMode.GOOD),
                new Item(3,"Backstage passes to a TAFKAL80ETC concert", 25, 20, "concert pass", 1, AgeingMode.GOOD),
        };

        processQualityForDays(items, 10);

        assertThat(items[0].sellIn , Is.is(5));
        assertThat(items[0].quality , Is.is(36));

        assertThat(items[1].sellIn , Is.is(0));
        assertThat(items[1].quality , Is.is(0));

        assertThat(items[2].sellIn , Is.is(-5));
        assertThat(items[2].quality , Is.is(0));

        assertThat(items[3].sellIn , Is.is(15));
        assertThat(items[3].quality , Is.is(30));

    }

    // FIXME: it does not work as required
    @Test
    public void updateQualityConjuredTest(){
        Item[] items = new Item[] {
                new Item(1,"Conjured Mana Cake", 3, 6, "conjured", 2, AgeingMode.BAD),
                new Item(2,"Conjured Mana Cake", 1, 1, "conjured",2, AgeingMode.BAD),
        };

        processQualityForDays(items, 2);

        assertThat(items[0].sellIn , Is.is(1));
        assertThat(items[0].quality , Is.is(2));

        assertThat(items[1].sellIn , Is.is(-1));
        assertThat(items[1].quality , Is.is(0));
    }
}