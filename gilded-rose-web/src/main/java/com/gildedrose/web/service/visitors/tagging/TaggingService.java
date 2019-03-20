package com.gildedrose.web.service.visitors.tagging;

import com.gildedrose.web.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class TaggingService {

    /**
     * Assign the tags depending on different criteria
     * @param withItems the list of items to tag.
     */
    public  void tagItems(List<ItemDTO> withItems){
        // group by item type resulting in a map of items by type
        Collector<ItemDTO, ?, Map<String, List<ItemDTO>>> groupBy = Collectors.groupingBy(o -> o.type);

        withItems.stream()
                .collect(groupBy)
                .forEach( (s, objects) -> {
                            if (objects.size() > 1) {
                                // is Highest Quality if quality and sell in is > 5
                                objects.stream().filter(itemDTO -> (itemDTO.quality > 5 && itemDTO.sellIn >5))
                                        .min(Comparator.<ItemDTO>comparingInt(o -> o.quality).reversed())
                                        .ifPresent(itemDTO -> itemDTO.tags.add("HQ"));
                                // is Lowest Quality if quality < 5  (only one item in list)
                                objects.stream().filter(itemDTO -> (!itemDTO.tags.contains("HQ") && itemDTO.quality < 5) )
                                        .min(Comparator.comparingInt(o -> o.quality))
                                        .ifPresent(itemDTO ->  itemDTO.tags.add("LQ") );
                            } else{
                                // is Lowest Quality if quality < 5 (only one item in list)
                                objects.stream().filter(itemDTO -> (itemDTO.quality < 5) )
                                        .findAny().ifPresent(itemDTO ->  itemDTO.tags.add("LQ") );
                            }
                            // is Sell In if sell in value < 5 (near to perish)
                            objects.stream().filter(itemDTO -> itemDTO.sellIn <= 5)
                                .forEach(itemDTO -> itemDTO.tags.add("SELL IN") );
                        }
                );

    }
}