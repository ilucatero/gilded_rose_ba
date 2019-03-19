package com.gildedrose.web.service.visitors.tagging;

import com.gildedrose.core.service.tagging.QualityTagVisitor;
import com.gildedrose.core.service.tagging.TagProcessor;
import com.gildedrose.web.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TaggingService {

    /**
     * Assign the tags depending on different criteria ( {@link QualityTagVisitor},.. )
     * @param withItems the list of items to tag.
     */
    public  void tagItems(List<ItemDTO> withItems){
        TagProcessor.with(withItems,
                QualityTagVisitor.getInstance(QualityTagVisitor.QUALITY_TAG.LQ, Comparator.comparingInt(o -> ((ItemDTO) o).quality))
                        .andThen(QualityTagVisitor.getInstance(QualityTagVisitor.QUALITY_TAG.LQ,
                                Comparator.comparingInt(o -> ((ItemDTO) o).quality).reversed())
                        ),
                QualityTagVisitor.getInstance(QualityTagVisitor.QUALITY_TAG.SELLIN, Comparator.comparingInt(o -> ((ItemDTO) o).sellIn))
        );
    }
}