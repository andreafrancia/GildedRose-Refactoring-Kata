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

    private Item updateItemQuality(Item item) {
        var updatedItem = new Item(item.name, item.sellIn, item.quality);

        if (!updatedItem.name.equals("Aged Brie")
                && !updatedItem.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (updatedItem.quality > 0 && !updatedItem.name.equals("Sulfuras, Hand of Ragnaros")) {
                updatedItem.quality = updatedItem.quality - 1;
            }
        } else {
            if (updatedItem.quality < 50) {
                updatedItem.quality = updatedItem.quality + 1;

                if (updatedItem.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (updatedItem.sellIn < 11 && updatedItem.quality < 50) {
                        updatedItem.quality = updatedItem.quality + 1;
                    }

                    if (updatedItem.sellIn < 6 && updatedItem.quality < 50) {
                        updatedItem.quality = updatedItem.quality + 1;
                    }
                }
            }
        }

        if (!updatedItem.name.equals("Sulfuras, Hand of Ragnaros")) {
            updatedItem.sellIn = updatedItem.sellIn - 1;
        }

        if (updatedItem.sellIn < 0) {
            if (!updatedItem.name.equals("Aged Brie")) {
                if (!updatedItem.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (updatedItem.quality > 0) {
                        if (!updatedItem.name.equals("Sulfuras, Hand of Ragnaros")) {
                            updatedItem.quality = updatedItem.quality - 1;
                        }
                    }
                } else {
                    updatedItem.quality = updatedItem.quality - updatedItem.quality;
                }
            } else {
                if (updatedItem.quality < 50) {
                    updatedItem.quality = updatedItem.quality + 1;
                }
            }
        }

        return updatedItem;
    }
}
