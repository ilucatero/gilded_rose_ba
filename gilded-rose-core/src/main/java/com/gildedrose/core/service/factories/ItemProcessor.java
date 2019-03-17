package com.gildedrose.core.service.factories;

import com.gildedrose.core.model.Item;

public interface ItemProcessor {

    /**
     * Process the quality of an item based on its attributes and type
     * @param items
     */
    public void updateQuality(Item items);

    
}
