package com.gildedrose.core.service.factories;


import com.gildedrose.core.model.Item;
import com.gildedrose.core.model.type.AgeingMode;

/**
 * Provides base functionality for the ItemProcessor
 */
public class ItemProcessorCheeseImp extends ItemProcessorAbstract{

    // TODO 1: use the new attributes to calculate Quality: agingDegree (always>0)

    /**
     * Compute the basic quality of a single item described on the specs
     * @param item
     */
    @Override
    protected void computeQuality(Item item) {
            super.computeQuality(item);
    }

}
