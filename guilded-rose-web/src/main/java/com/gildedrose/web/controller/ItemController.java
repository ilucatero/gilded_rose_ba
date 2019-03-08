package com.gildedrose.web.controller;

import com.guildedrose.core.model.Item;
import com.guildedrose.core.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller()
public class ItemController {

    // TODO: replace it with the spring bean
    protected ItemService itemService = new ItemService();

    @GetMapping("/get-items")
    @ResponseBody
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @GetMapping("/degrade/{itemId}")
    @ResponseBody
    public Boolean degrade(@PathVariable Long itemId){
        // TODO: complete functionality once springmvc is setup
        return itemService.degrade(itemId);
    }
}
