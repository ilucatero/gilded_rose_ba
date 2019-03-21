package com.gildedrose.core.service.factories.ItemProcessor;


import com.gildedrose.core.model.Item;

/**
 * Provides base functionality for the ItemProcessor
 */
public class ItemProcessorSulfurasImp extends ItemProcessorAbstract{

    @Override
    public void updateQuality(Item item){
        // "Sulfuras", being a legendary item, never has to be sold or decreases in Quality

        // DO NOTHING
    }

}
