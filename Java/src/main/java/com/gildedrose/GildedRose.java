package com.gildedrose;

class GildedRose {
    public static final int DEFAULT_SELL_IN_CHANGE = -1;
    public static final int DEFAULT_QUALITY_CHANGE = -1;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
            updateItemSellIn(item);
        }
    }

    private static void updateItemQuality(Item item) {
        if(item.name.equals(Item.SULFURAS)) return;

        int qualityDiff = DEFAULT_QUALITY_CHANGE;

        if(item.sellIn <= 0) {
            qualityDiff *= 2;
        }

        if(item.name.startsWith(Item.CONJURED_PREFIX)) {
            qualityDiff *= 2;
        }

        if(item.name.startsWith(Item.PASS_PREFIX)) {
            if (item.sellIn <= 0) {
                qualityDiff = -item.quality;
            } else if (item.sellIn <= 5) {
                qualityDiff *= -3;
            } else if (item.sellIn <= 10) {
                qualityDiff *= -2;
            }
        }

        if(item.name.startsWith(Item.AGED_PREFIX)) {
            // Aged gain quality
            qualityDiff *= -1;
        }

        item.quality = item.quality + qualityDiff;
        item.quality = Math.min(item.quality, 50);
        item.quality = Math.max(item.quality, 0);
    }

    private static void updateItemSellIn(Item item) {
        if(item.name.equals(Item.SULFURAS)) return;

        item.sellIn = item.sellIn + DEFAULT_SELL_IN_CHANGE;
    }
}
