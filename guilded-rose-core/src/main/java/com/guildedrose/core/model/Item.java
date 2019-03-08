package com.guildedrose.core.model;

public class Item {
    public int id;
    public String name;
    public int sellIn;
    public int quality;

    public Item(String name, int sellIn, int quality) {
        this(0, name, sellIn, quality);
    }
    public Item(int id, String name, int sellIn, int quality) {
        this.id = id;
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "id:"+ this.id + ", name:" +this.name + ", sellIn:" + this.sellIn + ", quality:" + this.quality;
    }
}
