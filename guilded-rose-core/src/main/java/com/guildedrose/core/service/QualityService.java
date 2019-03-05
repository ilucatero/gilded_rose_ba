package com.guildedrose.core.service;

import com.guildedrose.core.model.Item;

public class GildedRoseQualityServie {

    private GildedRoseQualityServie() {
    }

    public static void updateQuality(Item[] items) {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")) {
                if (items[i].quality > 0) {
                    items[i].quality = items[i].quality - 1;
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }
            }

            items[i].sellIn = items[i].sellIn - 1;

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (items[i].quality > 0) {
                        items[i].quality = items[i].quality - 1;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}