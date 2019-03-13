package com.gildedrose.core.service.factories;

import com.gildedrose.core.model.Item;

/**
 * Provides base functionality for the ItemProcessor
 */
public abstract class ItemProcessorAbstract implements ItemProcessor {

    // TODO implement base updateQuality functionality (use current QualityManagerService class)

    /**
     * Process the quality of an item based on its attributes and type
     * @param items
     */
    @Override
    public void updateQuality(Item[] items){

    }

    
}
