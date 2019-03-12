package com.gildedrose.web.adapter.dto;

import com.gildedrose.core.model.Item;
import com.gildedrose.web.dto.ItemDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * Abstract class that do the base objects transformations from/to DTO-ModelObject. <br>
 * Notice this uses spring @Autowired/@Bean to inject objects, so all classes that extends this Abstract class must
 * use the @Component annotation.
 */
public abstract class AbstractDtoAdapter implements DtoAdapter<Item, ItemDTO> {

    @Autowired
    protected ModelMapper modelMapper;

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        return modelMapper;
    }

}
