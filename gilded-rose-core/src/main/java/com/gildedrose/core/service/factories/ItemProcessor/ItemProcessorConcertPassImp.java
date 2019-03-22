package com.gildedrose.core.service.factories.ItemProcessor;


import com.gildedrose.core.model.Item;
import com.gildedrose.core.model.type.AgeingMode;

/**
 * Provides base functionality for the ItemProcessor
 */
public class ItemProcessorConcertPassImp extends ItemProcessorAbstract{

    @Override
    public void updateQuality(Item item){
        if(item.ageingDegree<0){
            throw new IllegalArgumentException("The ageing degree must not be lower than zero.");
        }

        item.sellIn--;
        if (item.sellIn > 0 ) {

            if(item.sellIn == 9 || item.sellIn == 5){
                item.ageingDegree++;
            }
            computeQuality(item);

        } else{
            // sell in <= 0 means concert have past
            item.quality = 0;
        }
    }

}
