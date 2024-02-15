package com.gildedrose;

import java.util.stream.Stream;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        items = Stream.of(items).map(this::updateItemQuality).toArray(Item[]::new);
    }

    private static boolean isPass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private static boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private static Item increaseItemQuality(Item item, int increaseBy) {
        item.quality = !isSulfuras(item) ? Math.min(50, item.quality + increaseBy) : item.quality;
        return item;
    }

    private static Item decreaseItemQuality(Item item, int decreaseBy) {
        item.quality = !isSulfuras(item) ? Math.max(0, item.quality - decreaseBy) : item.quality;
        return item;
    }

    static interface SellInEvolution {
        int evolveSellIn(Item item);
    }

    static class NormalSellIn implements SellInEvolution {
        @Override
        public int evolveSellIn(Item item) {
            return item.sellIn - 1;
        }
    }

    static class Incorruptible implements SellInEvolution {
        @Override
        public int evolveSellIn(Item item) {
            return item.sellIn;
        }
    }

    private static SellInEvolution sellInFor(Item item) {
        if (!isSulfuras(item)) {
            return new NormalSellIn();
        } else {
            return new Incorruptible();
        }
    }

    private Item updateItemQuality(Item item) {
        var updatedItem = new Item(item.name, item.sellIn, item.quality);

        updatedItem.sellIn = sellInFor(updatedItem).evolveSellIn(updatedItem);

        if (isPass(updatedItem)) {
            switch (Integer.valueOf(updatedItem.sellIn)) {
                case Integer i when i < 0   -> decreaseItemQuality(updatedItem, updatedItem.quality);
                case Integer i when i < 5   -> increaseItemQuality(updatedItem, 3);
                case Integer i when i < 10  -> increaseItemQuality(updatedItem, 2);
                default                     -> increaseItemQuality(updatedItem, 1);
            }
        }

        if (!isAgedBrie(updatedItem) && !isPass(updatedItem)) {
            decreaseItemQuality(updatedItem, 1);
        }
        if (isAgedBrie(updatedItem)) {
            increaseItemQuality(updatedItem, 1);
        }

        if (updatedItem.sellIn < 0 && !isAgedBrie(updatedItem) && !isPass(updatedItem)) {
            decreaseItemQuality(updatedItem, 1);
        }

        if (updatedItem.sellIn < 0 && isAgedBrie(updatedItem)) {
            increaseItemQuality(updatedItem, 1);
        }

        return updatedItem;
    }
}
