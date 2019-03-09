package com.gildedrose.web.controller;

import com.guildedrose.core.model.Item;
import com.guildedrose.core.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    protected ItemService itemService;

    @RequestMapping(value={"/get-items"}, method = RequestMethod.GET)
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @RequestMapping(value={"/degrade/{itemId}"}, method = RequestMethod.PUT)
    public Boolean degrade(@PathVariable Long itemId){
        // TODO: complete functionality once springmvc is setup
        return itemService.degrade(itemId);
    }
}
