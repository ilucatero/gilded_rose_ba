package com.gildedrose.web.adapter;

import com.gildedrose.core.model.Item;
import com.gildedrose.core.model.type.AgeingMode;
import com.gildedrose.web.adapter.dto.AbstractDtoAdapter;
import com.gildedrose.web.dto.ItemDTO;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

/**
 * Implementation for an Item Adapter. This class represent an adapter for objects to be transformed into another. <br>
 * @see  {@link AbstractDtoAdapter} which have the base object transformation functionality.
 */
@Component
public class ItemAdapter extends AbstractDtoAdapter<Item, ItemDTO>{

    /**
     * Initialize the mapper with the respective mapper
     * @throws Exception
     */
    public void initIt() throws Exception {
        // add mapper DTO to Model
        Converter<ItemDTO, Item> dtoToModel = ctx -> {
            ItemDTO src = ctx.getSource();
            return ctx.getSource() == null
                    ? null
                    : new Item(src.id, src.name, src.sellIn, src.quality, src.type);
        };
        modelMapper.createTypeMap(ItemDTO.class, Item.class).setConverter(dtoToModel);
    }

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
