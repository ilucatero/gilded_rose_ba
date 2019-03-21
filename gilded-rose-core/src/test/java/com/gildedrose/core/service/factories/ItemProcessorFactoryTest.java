package com.gildedrose.core.service.factories;

import com.gildedrose.core.service.factories.ItemProcessor.ItemProcessor;
import com.gildedrose.core.service.factories.ItemProcessor.ItemProcessorSulfurasImp;
import com.gildedrose.core.service.factories.ItemProcessor.ItemProcessorDefaultImp;
import com.gildedrose.core.service.factories.ItemProcessor.ItemProcessorFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemProcessorFactoryTest {

    protected ItemProcessorFactory itemProcessorFactory;

    @Before
    public final void setUp() throws Exception {
        itemProcessorFactory = ItemProcessorFactory.getInstance();
    }

    @Test
    public void itemProcessorDefaultForKnownTypeTest(){
        ItemProcessor itemProcessor = itemProcessorFactory.getItemProcessor("vest");
        assertNotNull(itemProcessor);
        // should get the default
        assertTrue(itemProcessor.getClass().equals(ItemProcessorDefaultImp.class));
    }


    @Test
    public void itemProcessorDefaultForUnKnownTypeTest(){
        ItemProcessor itemProcessor = itemProcessorFactory.getItemProcessor("unknowingType");
        assertNotNull(itemProcessor);
        // should get the default
        assertTrue(itemProcessor.getClass().equals(ItemProcessorDefaultImp.class));
    }

    @Test
    public void itemProcessorForGivenTypeTest(){
        ItemProcessor itemProcessor = itemProcessorFactory.getItemProcessor("SulFuras");
        assertNotNull(itemProcessor);
        // should get the default
        assertTrue(itemProcessor.getClass().equals(ItemProcessorSulfurasImp.class));
    }
}