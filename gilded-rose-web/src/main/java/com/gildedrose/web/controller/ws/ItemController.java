package com.gildedrose.web.controller.ws;

import com.gildedrose.core.model.Item;
import com.gildedrose.core.service.ItemService;
import com.gildedrose.web.adapter.ItemAdapter;
import com.gildedrose.web.dto.ItemDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    protected ItemService itemService;

    @Autowired
    protected ItemAdapter itemAdapter;

    @RequestMapping(value={"/get-items"}, method = RequestMethod.GET)
    public List<ItemDTO> getItems(){

        // TODO : implement pagination
        List<Item> items = itemService.getItems();

        List<ItemDTO> dtoItems = items.stream().map(item -> itemAdapter.toDto(item)).collect(Collectors.toList());

        // TODO: add tags to respective itemDtos (as for now, tag only the Highest Quality item per type)

        return dtoItems ;
    }

    @RequestMapping(value={"/{itemId}"}, method = RequestMethod.GET)
    public ItemDTO get(@PathVariable Long itemId){
        // TODO: implement and use it on frontend after update values
        return null;
    }

    @RequestMapping(value={"/degrade/{itemId}"}, method = RequestMethod.PATCH)
    public Boolean degrade(@PathVariable Long itemId){
        // TODO: determine if returning the actual updated item is necessary on frontend
        return itemService.degrade(itemId);
    }
}
