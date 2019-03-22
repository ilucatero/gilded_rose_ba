package com.gildedrose.web.service.visitors.tagging;

import com.gildedrose.web.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class TaggingService {

    List<String> taggableTypes = Arrays.asList("SULFURAS");
    /**
     * Assign the tags depending on different criteria
     * @param withItems the list of items to tag.
     */
    public  void tagItems(List<ItemDTO> withItems){
        // group by item type resulting in a map of items by type
        Collector<ItemDTO, ?, Map<String, List<ItemDTO>>> groupByType = Collectors.groupingBy(o -> o.type);
        Collector<ItemDTO, ?, Map<String, List<ItemDTO>>> groupByName = Collectors.groupingBy(o -> o.name);

        withItems.stream()
                .collect(groupByType)
                .forEach( (s, objects) -> {
                        if (!taggableTypes.contains(s.toUpperCase())) {
                            objects.parallelStream().collect(groupByName).forEach((s1, itemDTOS) -> {
                                if (itemDTOS.size() > 1) {
                                        // is Highest Quality if quality and sell in is > 5
                                        itemDTOS.stream().filter(itemDTO -> (itemDTO.quality > 5 && itemDTO.sellIn > 5))
                                                .min(Comparator.<ItemDTO>comparingInt(o -> o.quality).reversed())
                                                .ifPresent(itemDTO -> {
                                                    itemDTOS.stream()
                                                            .filter(itemDTO1 -> (itemDTO1.quality == itemDTO.quality ))
                                                            .forEach(itemDTO1 -> itemDTO1.tags.add("HQ"));
                                                });
                                        // is Lowest Quality if quality < 5  (only one item in list)
                                        itemDTOS.stream().filter(itemDTO -> (!itemDTO.tags.contains("HQ") && itemDTO.quality < 5))
                                                .min(Comparator.comparingInt(o -> o.quality))
                                                .ifPresent(itemDTO -> itemDTO.tags.add("LQ"));
                                } else {
                                    // is Lowest Quality if quality < 5 (only one item in list)
                                    itemDTOS.stream().filter(itemDTO -> (itemDTO.quality < 5))
                                            .findAny().ifPresent(itemDTO -> itemDTO.tags.add("LQ"));
                                }
                                // is Sell In if sell in value < 5 (near to perish)
                                itemDTOS.stream().filter(itemDTO -> itemDTO.sellIn <= 5)
                                        .forEach(itemDTO -> itemDTO.tags.add("SELL IN"));
                            });
                        }
                    }
                );

    }
}