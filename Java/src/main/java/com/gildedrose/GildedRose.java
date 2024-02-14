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

    private boolean isPass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private Item increaseItemQuality(Item item, int increaseBy) {
        if (!isSulfuras(item) && item.quality < 50) {
            item.quality += increaseBy;
        }

        return item;
    }

    private Item decreaseItemQuality(Item item, int decreaseBy) {
        if (!isSulfuras(item) && item.quality > 0) {
            item.quality -= decreaseBy;
        }

        return item;
    }

    private Item updateItemQuality(Item item) {
        var updatedItem = new Item(item.name, item.sellIn, item.quality);

        if (!isAgedBrie(updatedItem) && !isPass(updatedItem)) {
            decreaseItemQuality(updatedItem, 1);
        } else {
            increaseItemQuality(updatedItem, 1);

            if (isPass(updatedItem)) {
                if (updatedItem.sellIn < 11) {
                    increaseItemQuality(updatedItem, 1);
                }

                if (updatedItem.sellIn < 6) {
                    increaseItemQuality(updatedItem, 1);
                }
            }
        }

        if (!isSulfuras(updatedItem)) {
            updatedItem.sellIn = updatedItem.sellIn - 1;
        }

        if (updatedItem.sellIn < 0) {
            if (!isAgedBrie(updatedItem)) {
                if (!isPass(updatedItem)) {
                    decreaseItemQuality(updatedItem, 1);
                } else {
                    decreaseItemQuality(updatedItem, updatedItem.quality);
                }
            } else {
                increaseItemQuality(updatedItem, 1);
            }
        }

        return updatedItem;
    }
}
