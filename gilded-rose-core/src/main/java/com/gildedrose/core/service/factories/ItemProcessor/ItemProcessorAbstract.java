package com.gildedrose.core.service.factories.ItemProcessor;

import com.gildedrose.core.model.Item;
import com.gildedrose.core.model.type.AgeingMode;

/**
 * Provides base functionality for the ItemProcessor
 */
public abstract class ItemProcessorAbstract implements ItemProcessor {

    // TODO 1: use the new attributes to calculate Quality: ageingDegree (always>0)


    /**
     * Process the quality of an item based on its attributes and type
     * @param item
     */
    @Override
    public void updateQuality(Item item){
        if(item.ageingDegree<0){
            throw new IllegalArgumentException("The ageing degree must not be lower than zero.");
        }
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
    protected void computeQuality(Item item) {
        for (int i = 0; i < item.ageingDegree; i++) {
            if (item.ageingMode.equals(AgeingMode.BAD)) {
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
}
