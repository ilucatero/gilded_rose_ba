package com.gildedrose.core.service;

import com.gildedrose.core.dao.Dao;
import com.gildedrose.core.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemService {

    @Autowired
    protected Dao<Item> itemDao;

    @Autowired
    protected QualityManagerService qualityManagerService;

    public List<Item> getItems(){
        return itemDao.getAll();
    }

    public List<Item> get(List<Long> ids){
        return itemDao.find(ids);
    }

    public boolean degrade(Long id) throws IllegalArgumentException{
        // TODO: add the required functionality
        log.info("Degrading id:{}",id);
        Optional<Item> opItem = itemDao.get(id);
        if (opItem.isPresent() ) {
            Item item = opItem.get();
            qualityManagerService.updateQuality(opItem.get());
            return itemDao.update(item, new String[]{"quality"});
        }

        throw new IllegalArgumentException("The passed value doesnt exist.");
    }
}