package com.gildedrose.core.dao;

import com.gildedrose.core.model.Item;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/* TODO: At the moment the db only a basic list of items stored on memory, so when restart the values are lost.
   TODO: So, here the real implementation of this DAO will be required
*/
@Component("ItemDao")
public class ItemDao implements Dao<Item> {

    // TODO: this only represent the real db, should ONLY be use for tests and be removed later on
    private static List<Item> items = Arrays.asList(
            new Item(1,"+5 Dexterity Vest", 10, 20),
            new Item(2,"Aged Brie", 2, 0),
            new Item(3,"Elixir of the Mongoose", 5, 7),
            new Item(4,"Sulfuras, Hand of Ragnaros", 0, 80),
            new Item(5,"Sulfuras, Hand of Ragnaros", -1, 80),
            new Item(6,"Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item(7,"Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item(8,"Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item(9,"Conjured Mana Cake", 3, 6)
    );

    @Override
    public Optional<Item> get(long id) {
        // TODO: implement functionality as required
        return items.stream()
                .filter(item -> (id == item.id))
                .findAny();
    }

    @Override
    public List<Item> getAll() {
        // TODO: implement functionality as required
        return items;
    }

    @Override
    public void save(Item item) {
        // TODO: implement functionality as required
        items.add(item);
    }

    @Override
    public boolean update(Item item, String[] params) {
        // TODO: implement functionality as required

        // TODO: once the db resource is implemented remove below code and do the actual db update
        Optional<Item> itemOptional = get(item.id);
        if (itemOptional.isPresent()) {
            for (String param : params) {
                switch (param ) {
                    case "name" : itemOptional.get().name = item.name; return true;
                    case "sellIn" : itemOptional.get().sellIn = item.sellIn; return true;
                    case "quality" : itemOptional.get().quality = item.quality; return true;
                    default:
                }
            }
        }
        return false;
    }

    @Override
    public void delete(Item item) {
        // TODO: implement functionality as required
        items.remove(item);
    }
}
