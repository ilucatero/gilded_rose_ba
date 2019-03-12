package com.gildedrose.web.dto;

import java.util.List;

public class ItemDTO {

    public int id;
    public String name;
    public int sellIn;
    public int quality;
    public List<String> tags;

    public ItemDTO() {
    }
    public ItemDTO(int id, String name, int sellIn, int quality) {
        this.id = id;
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

}
