package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            boolean isConjured = item.name.startsWith("Conjured");

            if (!item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                updatePerishingItemQuality(item, isConjured);
            } else if (item.quality < 50) {
                item.quality = item.quality + 1;

                updateBackstageQuality(item);
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (item.name.equals("Aged Brie")) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                } else {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        updatePerishingItemQuality(item, isConjured);
                    } else {
                        item.quality = 0;
                    }
                }
            }
        }
    }

    private static void updateBackstageQuality(Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn < 11 && item.quality < 50) {
                item.quality++;
            }

            if (item.sellIn < 6 && item.quality < 50) {
                item.quality++;
            }
        }
    }

    private static void updatePerishingItemQuality(Item item, boolean isConjured) {
        if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
                int qualityUpdate = isConjured ? 2 : 1;
                item.quality = item.quality - qualityUpdate;
        }
    }
}
