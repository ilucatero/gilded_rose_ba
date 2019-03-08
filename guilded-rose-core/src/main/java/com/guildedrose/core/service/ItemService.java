package com.guildedrose.core.service;

import com.guildedrose.core.dao.Dao;
import com.guildedrose.core.dao.ItemDao;
import com.guildedrose.core.model.Item;

import java.util.List;

public class ItemService {

    // TODO: replace by the spring bean
    protected Dao<Item> itemDao  = new ItemDao();

    public List<Item> getItems(){
        return itemDao.getAll();
    }
}