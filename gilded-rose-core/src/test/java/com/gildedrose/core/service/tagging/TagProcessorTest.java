package com.gildedrose.core.service.tagging;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TagProcessorTest {

    private List<ItemTest> itemsToTag;

    @Before
    public final void setUp() throws Exception {
        itemsToTag = Arrays.asList(
                new ItemTest(1, "itemA_1", "type1", 4),
                new ItemTest(1, "itemA_2","type1",8),
                new ItemTest(1, "itemA_4","type1",1),
                new ItemTest(1, "itemA_5","type1",1),

                new ItemTest(1, "itemB_1", "type2", 4),
                new ItemTest(1, "itemB_2","type2",8),
                new ItemTest(1, "itemB_3","type2",1),
                new ItemTest(1, "itemB_4","type2",1)
        );
    }

    @Test
    public final void testHighQualityTagging(){

        Collector<ItemTest, ?, Map<String, List<ItemTest>>> groupBy = Collectors.groupingBy(o -> o.type);

        TagProcessor.with(itemsToTag,
                QualityTagFunction.getInstance(QualityTagFunction.QUALITY_TAG.LQ, groupBy, Comparator.comparingInt(o -> o.quality)),
                QualityTagFunction.getInstance(QualityTagFunction.QUALITY_TAG.HQ, groupBy, Comparator.<ItemTest>comparingInt(o -> o.quality).reversed())
        );

        // check item list is not emptied
        assertFalse(itemsToTag.isEmpty());

        // check first item has no tags added
        assertNotNull(itemsToTag.get(0).tagList);
        assertTrue(itemsToTag.get(0).tagList.isEmpty());
        // check second item as the HQ tag
        assertFalse(itemsToTag.get(1).tagList.isEmpty());
        assertThat(itemsToTag.get(1).tagList.get(0), Is.is("HQ") );
        // check third item as the LQ tag
        assertFalse(itemsToTag.get(2).tagList.isEmpty());
        assertThat(itemsToTag.get(2).tagList.get(0), Is.is("LQ") );
    }


}