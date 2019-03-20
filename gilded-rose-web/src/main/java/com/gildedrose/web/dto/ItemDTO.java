package com.gildedrose.web.dto;

import java.util.ArrayList;
import java.util.List;

public class ItemDTO  {

    public long id;
    public String name;
    public int sellIn;
    public int quality;
    public String type;
    public List<String> tags;

    public ItemDTO() {
        this(0,null,0,0,null);
    }

    public ItemDTO(long id, String name, int sellIn, int quality, String type) {
        this.id = id;
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.type = type;
        this.tags = new ArrayList<>(0);
    }

}
