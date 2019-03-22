package com.gildedrose.core.service;

import com.gildedrose.core.model.Item;
import com.gildedrose.core.service.factories.ItemProcessor.ItemProcessorFactory;

import java.util.List;

public class QualityManagerService {

    protected ItemProcessorFactory itemProcessorFactory = ItemProcessorFactory.getInstance();

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
    public void updateQuality(List<Item> items) {
        items.parallelStream().forEach(item -> updateQuality(item));
    }

    /**
     * Implementation for single item.
     * see {@link #updateQuality(List)}
     * @param item
     */
    public void updateQuality(Item item) {
            itemProcessorFactory .getItemProcessor(item.type).updateQuality(item);
    }

}