package com.gildedrose.core.service;

import com.gildedrose.core.model.Item;

public class QualityManagerService {

    // TODO 1: use the new attributes to calculate Quality: type & agingDegree (always>0) & aging mode(good or bad)
    // TODO 2: since most of db gives a list of objects, change array to list to avoid unnecessary transformations
    // TODO 3: to avoid multiple switch/ifs depending on type, generate a QualtyProcessorFactory class based on type

    /**
     * Update Quality of each item following the below constraints: <ul>
     *  <li>Once the sell by date has passed, Quality degrades twice as fast</li>
     *  <li>The Quality of an item is never negative</li>
     *  <li>"Aged Brie" actually increases in Quality the older it gets</li>
     *  <li>The Quality of an item is never more than 50</li>
     *  <li>"Sulfuras", being a legendary item, never has to be sold or decreases in Quality</li>
     *  <li>"Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
     *      Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less
     *      but Quality drops to 0 after the concert.</li>
     * </ul>
     * @param items
     */
    public void updateQuality(Item[] items) {
        for (Item item : items) {
            computeQuality(item);

            item.sellIn--;

            // quality is double computed if sellIn < 0
            if (item.sellIn < 0) {
                computeQuality(item);
            }
        }
    }

    /**
     * Compute the basic quality of a single item described on the specs
     * @param item
     */
    private void computeQuality(Item item) {
        if (!"Aged Brie".equals(item.name)) {
            if (item.quality > 0) {
                item.quality--;
            }
        } else {
            if (item.quality < 50) {
                item.quality++;
            }
        }
    }
}