package com.gildedrose.web.adapter;

import com.gildedrose.core.model.Item;
import com.gildedrose.web.adapter.dto.AbstractDtoAdapter;
import com.gildedrose.web.dto.ItemDTO;
import org.springframework.stereotype.Component;

/**
 * Implementation for an Item Adapter. This class represent an adapter for objects to be transformed into another. <br>
 * @see  {@link AbstractDtoAdapter} which have the base object transformation functionality.
 */
@Component
public class ItemAdapter extends AbstractDtoAdapter{

    @Override
    public ItemDTO toDto(Item itemModel) {
        if(itemModel == null){
            return null;
        }
        return  modelMapper.map(itemModel, ItemDTO.class);
    }

    @Override
    public Item toModel(ItemDTO itemDto) {
        if(itemDto == null){
            return null;
        }
        return modelMapper.map(itemDto, Item.class);
    }
}
