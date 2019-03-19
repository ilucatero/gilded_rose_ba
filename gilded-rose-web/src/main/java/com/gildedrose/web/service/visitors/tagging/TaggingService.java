package com.gildedrose.web.service.visitors.tagging;

import com.gildedrose.core.service.tagging.QualityTagFunction;
import com.gildedrose.core.service.tagging.TagProcessor;
import com.gildedrose.web.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class TaggingService {

    /**
     * Assign the tags depending on different criteria ( {@link QualityTagFunction},.. )
     * @param withItems the list of items to tag.
     */
    public  void tagItems(List<ItemDTO> withItems){
        // group by item type resulting in a map of items by type
        Collector<ItemDTO, ?, Map<String, List<ItemDTO>>> groupBy = Collectors.groupingBy(o -> o.type);

        // create functions for each tag to apply
        Function qualityTagHQ = QualityTagFunction.getInstance(QualityTagFunction.QUALITY_TAG.HQ, groupBy,
                Comparator.<ItemDTO>comparingInt(o -> o.quality).reversed());
        Function qualityTagLQ = QualityTagFunction.getInstance(QualityTagFunction.QUALITY_TAG.LQ, groupBy,
                Comparator.comparingInt(o -> o.quality)).compose(o ->
                ((List<ItemDTO>)o).stream().filter(itemDTO -> !itemDTO.tags.contains("HQ")).collect(Collectors.toList())
        );

        // add sellIn tag if the value is < 10 (filter then tag)
        Function qualityTagSellIn = QualityTagFunction.getInstance(QualityTagFunction.QUALITY_TAG.SELLIN, groupBy,
                Comparator.comparingInt(o -> o.sellIn)).compose(o ->
                    ((List<ItemDTO>)o).stream().filter(itemDTO -> itemDTO.sellIn<10).collect(Collectors.toList())
                );

        // chain functions HQ & LQ
        Function qualityTagVisitorHQLQ = qualityTagHQ.andThen(qualityTagLQ);

        // run tag executor
        TagProcessor.with(withItems, qualityTagVisitorHQLQ, qualityTagSellIn);

    }
}