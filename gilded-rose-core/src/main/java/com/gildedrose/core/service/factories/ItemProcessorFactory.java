package com.gildedrose.core.service.factories;

import java.util.HashMap;
import java.util.Map;

/**
 * Based on type gives a {@link ItemProcessor} that could perform an updateQuality action
 */
public class ItemProcessorFactory{
    private static ItemProcessorFactory instance;

    final ItemProcessor  defaultItemProcessor;
    final private Map<String, ItemProcessor> processorMap;

    private ItemProcessorFactory(){
        defaultItemProcessor = new ItemProcessorDefaultImp();
        processorMap = new HashMap<>();

        // TODO: initialize a map with all required ItemProcessors
        processorMap.put("CHEESE", new ItemProcessorCheeseImp());
    }

    /**
     * Gets the existence instance of this singleton class.
     * @return
     */
    public static ItemProcessorFactory getInstance() {
        if(instance == null) {
            // TODO double check for singleton with synchronize
            instance = new ItemProcessorFactory();
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

        ItemProcessor itemProcessor = processorMap.getOrDefault(itemType.toUpperCase(), defaultItemProcessor);
        return itemProcessor;
    }
}
