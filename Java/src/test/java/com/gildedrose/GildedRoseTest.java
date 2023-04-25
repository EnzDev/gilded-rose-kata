package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void testItemToString() {
        Item item = new Item("foo", 10, 0);

        assertEquals("foo, 10, 0", item.toString());
    }

    /**
     * sellIn: -1 par update
     */
    @Test
    void testSellInUpdate() {
        Item[] items = new Item[] { new Item("foo", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
    }

    /**
     * quality: -1 par update
     */
    @Test
    void testQualityUpdate() {
        Item[] items = new Item[] { new Item("foo", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].quality);
    }

    /**
     *  sellIn est atteint (0), qualité se dégrade 2x plus vite
     */
    @Test
    void testQualityUpdateExpiredItem() {
        Item[] items = new Item[] { new Item("foo", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }

    /**
     * "Aged Brie" qualité augmente.
     */
    @Test
    void testAgedBrieQualityIncreases() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
    }

    /**
     * "Aged Brie" qualité augmente.
     */
    @Test
    void testAgedBrieQualityIncreasesTwiceWhenExpired() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
    }


    /**
     * "Sulfuras" statistiques ne bougent pas
     */
    @Test
    void testSulfurasNeverExpire() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, app.items[0].sellIn);
    }

    /**
     * "Sulfuras" statistiques ne bougent pas
     */
    @Test
    void testSulfurasQualityNeverChange() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }


    /**
     * * "Backstage pass":
     *   sellIn <= 10 : quality + 2
     *   sellIn <= 5 : quality + 3
     *   sellIn = 0 : quality = 0
     */
    @Test
    void testBackstageTicketSellIn10Quality() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
    }

    @Test
    void testBackstageTicketSellIn5Quality() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
    }

    @Test
    void testBackstageTicketExpiredQuality() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }


    /**
     * qualité min: 0
     */
    @Test
    void testQualityMin() {
        Item[] items = new Item[] { new Item("foo", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    /**
     * qualité max: 50
     */
    @Test
    void testQualityMax() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testConjuredItemQuality() {
        Item[] items = new Item[] { new Item("Conjured foo", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void testConjuredItemExpiredQuality() {
        Item[] items = new Item[] { new Item("Conjured foo", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(6, app.items[0].quality);
    }
    @Test
    void testConjuredItemSellIn() {
        Item[] items = new Item[] { new Item("Conjured foo", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
    }
}
