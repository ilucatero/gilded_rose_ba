package com.gildedrose.core.service.factories.ItemProcessor;

import lombok.Synchronized;

import java.util.HashMap;
import java.util.Map;

/**
 * Based on type gives a {@link ItemProcessor} that could perform an updateQuality action
 */
public class ItemProcessorFactory{
    private static ItemProcessorFactory instance;

    private final static ItemProcessor DEFAULT_ITEM_PROCESSOR = new ItemProcessorDefaultImp();
    private final Map<String, ItemProcessor> PROCESSOR_MAP = new HashMap<>();

    private ItemProcessorFactory(){
        // TODO: initialize a map with all required ItemProcessors
        PROCESSOR_MAP.put("SULFURAS", new ItemProcessorSulfurasImp());
        PROCESSOR_MAP.put("CONCERT PASS", new ItemProcessorConcertPassImp());
    }

    /**
     * Gets the existence instance of this singleton class.
     * @return
     */
    public static ItemProcessorFactory getInstance() {
        if(instance == null) {
            synchronized(ItemProcessorFactory.class) {
                if(instance == null) {
                    instance = new ItemProcessorFactory();
                }
            }
        }
        return instance;
    }

    /**
     * Return the item processor based on its type.
     * @param itemType
     * @return
     */
    public ItemProcessor getItemProcessor(String itemType){
        if(itemType == null || itemType.isEmpty()){
            return null;
        }

        ItemProcessor itemProcessor = PROCESSOR_MAP.getOrDefault(itemType.toUpperCase(), DEFAULT_ITEM_PROCESSOR);
        return itemProcessor;
    }
}
