package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            items[i] = updateItemQuality(items[i]);
        }
    }

    private boolean isPass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private Item updateItemQuality(Item item) {
        var updatedItem = new Item(item.name, item.sellIn, item.quality);

        if (!isPass(updatedItem) && !updatedItem.name.equals("Sulfuras, Hand of Ragnaros")
                && !updatedItem.name.equals("Aged Brie")) {
            if (updatedItem.quality > 0) {
                updatedItem.quality = updatedItem.quality - 1;
            }
            updatedItem.sellIn = updatedItem.sellIn - 1;

            if (updatedItem.sellIn < 0) {
                if (updatedItem.quality > 0) {
                    updatedItem.quality = updatedItem.quality - 1;
                }
            }
        }

        if (isPass(updatedItem)) {

            if (updatedItem.quality < 50) {
                updatedItem.quality = updatedItem.quality + 1;

                if (updatedItem.sellIn < 11 && updatedItem.quality < 50) {
                    updatedItem.quality = updatedItem.quality + 1;
                }

                if (updatedItem.sellIn < 6 && updatedItem.quality < 50) {
                    updatedItem.quality = updatedItem.quality + 1;
                }
            }
            updatedItem.sellIn = updatedItem.sellIn - 1;

            if (updatedItem.sellIn < 0) {
                updatedItem.quality = updatedItem.quality - updatedItem.quality;
            }
        }

        if (updatedItem.name.equals("Aged Brie")) {

            if (updatedItem.quality < 50) {
                updatedItem.quality = updatedItem.quality + 1;

            }
            updatedItem.sellIn = updatedItem.sellIn - 1;

            if (updatedItem.sellIn < 0) {
                if (updatedItem.quality < 50) {
                    updatedItem.quality = updatedItem.quality + 1;
                }
            }
        }

        return updatedItem;
    }
}
