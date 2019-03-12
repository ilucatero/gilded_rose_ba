package com.gildedrose.web.dto;

import java.util.List;

public class ItemDTO {

    public long id;
    public String name;
    public int sellIn;
    public int quality;
    public List<String> tags;

    public ItemDTO() {
    }
    public ItemDTO(long id, String name, int sellIn, int quality) {
        this.id = id;
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

}
