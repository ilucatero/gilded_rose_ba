package com.gildedrose.core.dao;

import com.gildedrose.core.model.Item;
import com.gildedrose.core.model.type.AgeingMode;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* TODO: At the moment the db only a basic list of items stored on memory, so when restart the values are lost.
   TODO: So, here the real implementation of this DAO will be required
*/
@Component("ItemDao")
public class ItemDao_nodb implements Dao<Item> {

    // TODO: this only represent the real db, should ONLY be use for tests and be removed later on
    private static List<Item> items = Arrays.asList(
            new Item(1,"+5 Dexterity Vest", 10, 20, "vest"),
            new Item(2,"Aged Brie", 2, 0, "cheese", 1, AgeingMode.GOOD),
            new Item(3,"Elixir of the Mongoose", 5, 7, "normal power drink"),
            new Item(4,"Sulfuras, Hand of Ragnaros", 0, 80, "sulfuras"),
            new Item(5,"Sulfuras, Hand of Ragnaros", -1, 80, "sulfuras"),
            new Item(6,"Backstage passes to a TAFKAL80ETC concert", 15, 20, "concert pass",1, AgeingMode.GOOD),
            new Item(7,"Backstage passes to a TAFKAL80ETC concert", 10, 49, "concert pass",1, AgeingMode.GOOD),
            new Item(8,"Backstage passes to a TAFKAL80ETC concert", 5, 49, "concert pass",1, AgeingMode.GOOD),
            new Item(9,"Conjured Mana Cake", 3, 6, "conjured",2, AgeingMode.BAD),
            new Item(10,"Cooking Book", 6, 8, "Cooking Book"),
            new Item(11,"Cooking Book", 6, 8, "Cooking Book"),
            new Item(12,"Jazz Book", 20, 10, "Music Book"),
            new Item(13,"Jazz Book", 20, 10, "Music Book"),
            new Item(14,"Blues Book", 20, 9, "Music Book"),
            new Item(15,"Blues Book", 20, 9, "Music Book"),
            new Item(16,"Blues Book", 20, 8, "Music Book")
    );

    @Override
    public List<Item> find(List<Long> ids) {
        // TODO: implement functionality as required
        return items.stream()
                .filter(item -> (ids.contains(item.id)))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Item> get(Long id) {
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
    public Item save(Item item) {
        // TODO: implement functionality as required
        items.add(item);
        return item;
    }

    @Override
    public Item update(Item item, String[] params) {
        // TODO: implement functionality as required

        // TODO: once the db resource is implemented remove below code and do the actual db update
        Optional<Item> itemOptional = get(item.id);
        if (itemOptional.isPresent()) {
            Item updateItem = itemOptional.get();
            for (String param : params) {
                switch (param) {
                    case "name":
                        updateItem.name = item.name;
                        break;
                    case "sellIn":
                        updateItem.sellIn = item.sellIn;
                        break;
                    case "quality":
                        updateItem.quality = item.quality;
                        break;
                    default:
                }
            }
            return updateItem;
        }
        return null;
    }

    @Override
    public <S extends Item> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Item> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Item> findAll() {
        return null;
    }

    @Override
    public Iterable<Item> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Item item) {
        // TODO: implement functionality as required
        items.remove(item);
    }

    @Override
    public void deleteAll(Iterable<? extends Item> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
