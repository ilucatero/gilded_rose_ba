package com.gildedrose.core.model;

public class Item {
    public long id;
    public String name;
    public int sellIn;
    public int quality;
    // TODO: add attributes : type & agingDegree (always>0) & aging mode(good or bad)

    public Item(String name, int sellIn, int quality) {
        this(0, name, sellIn, quality);
    }
    public Item(int id, String name, int sellIn, int quality) {
        this.id = id;
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    // TODO: implement hashcode & equals methods

    @Override
    public String toString() {
        return "id:"+ this.id + ", name:" +this.name + ", sellIn:" + this.sellIn + ", quality:" + this.quality;
    }
}
