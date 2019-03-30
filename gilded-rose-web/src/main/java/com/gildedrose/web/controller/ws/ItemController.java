package com.gildedrose.web.controller.ws;

import com.gildedrose.core.model.Item;
import com.gildedrose.core.service.ItemService;
import com.gildedrose.web.service.visitors.tagging.TaggingService;
import com.gildedrose.web.adapter.ItemAdapter;
import com.gildedrose.web.dto.ItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    protected ItemService itemService;

    @Autowired
    protected ItemAdapter itemAdapter;

    @Autowired
    protected TaggingService taggingService;

    @RequestMapping(value={"/get-items"}, method = RequestMethod.GET)
    public List<ItemDTO> getAll(){

        // TODO : implement pagination
        List<Item> items = itemService.getItems();

        List<ItemDTO> dtoItems = items.stream().map(item -> itemAdapter.toDto(item)).collect(Collectors.toList());

        taggingService.tagItems(dtoItems);

        return dtoItems ;
    }

    @RequestMapping(value={"/{itemIds}"}, method = RequestMethod.GET)
    public ResponseEntity<List<ItemDTO>> getList(@PathVariable List<Long> itemIds){

        List<Item> items = itemService.get(itemIds);
        if (!items.isEmpty()) {
            List<ItemDTO> dtoItems = items.stream().map(item -> itemAdapter.toDto(item)).collect(Collectors.toList());

            return ResponseEntity.ok(dtoItems);
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value={"/degrade/{itemId}"}, method = RequestMethod.PATCH)
    public ResponseEntity<Boolean> degrade(@PathVariable Long itemId){
        try {
            boolean degrade = itemService.degrade(itemId);
            return ResponseEntity.ok(degrade);
        }catch (IllegalArgumentException e){
            log.info("The id {} to degrade was not found", itemId);
        }
        return ResponseEntity.notFound().build();
    }
}
