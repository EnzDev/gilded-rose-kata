package com.gildedrose;



public class Item {
    public static String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public static String CONJURED_PREFIX = "Conjured ";
    public static String AGED_PREFIX = "Aged ";
    public static String PASS_PREFIX = "Backstage ";


    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
