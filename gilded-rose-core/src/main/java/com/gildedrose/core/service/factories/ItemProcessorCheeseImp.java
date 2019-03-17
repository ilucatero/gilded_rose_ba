package com.gildedrose.core.service.factories;


import com.gildedrose.core.model.Item;

/**
 * Provides base functionality for the ItemProcessor
 */
public class ItemProcessorCheeseImp extends ItemProcessorAbstract{


    /**
     * Process the quality of an item based on its attributes and type
     * @param item
     */
    @Override
    public void updateQuality(Item item){
        computeQuality(item);

        item.sellIn--;

        // quality is double computed if sellIn < 0
        if (item.sellIn < 0) {
            computeQuality(item);
        }
    }

    /**
     * Compute the basic quality of a single item described on the specs
     * @param item
     */
    private void computeQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }
}
