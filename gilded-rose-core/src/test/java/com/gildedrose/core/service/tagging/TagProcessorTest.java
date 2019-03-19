package com.gildedrose.core.service.tagging;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TagProcessorTest {

    private List<ItemTest> itemsToTag;

    @Before
    public final void setUp() throws Exception {
        itemsToTag = Arrays.asList(
                new ItemTest(1, "item1", 4),
                new ItemTest(2, "item2",8),
                new ItemTest(3, "item3",1)
        );
    }

    @Test
    public final void testHighQualityTagging(){
        TagProcessor.with(itemsToTag,
                QualityTagVisitor.getInstance(QualityTagVisitor.QUALITY_TAG.LQ, Comparator.comparingInt(o -> ((ItemTest) o).quality)),
                QualityTagVisitor.getInstance(QualityTagVisitor.QUALITY_TAG.HQ, Comparator.comparingInt(o -> ((ItemTest) o).quality).reversed())
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