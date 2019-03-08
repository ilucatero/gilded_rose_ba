package com.guildedrose.core.dao;

import com.guildedrose.core.model.Item;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/* TODO: At the moment the db only a basic list of items stored on memory, so when restart the values are lost.
   TODO: So, here the real implementation of this DAO will be required
*/
public class ItemDao implements Dao<Item> {

    // TODO: this only represent the real db, should ONLY be use for tests and be removed later on
    private List<Item> items = Arrays.asList(
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
        return Optional.empty();
    }

    @Override
    public List<Item> getAll() {
        return items;
    }

    @Override
    public void save(Item item) {
        items.add(item);
    }

    @Override
    public void update(Item item, String[] params) {

    }

    @Override
    public void delete(Item item) {
        items.remove(item);
    }
}
