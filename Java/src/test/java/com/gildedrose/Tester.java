package com.gildedrose;

public class Tester {
    private final LinePrinter printer;
    Tester(LinePrinter printer)
    {
        this.printer = printer;
    }

    public void testAll(int days)
    {
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            new Item("Conjured Mana Cake", 3, 6) };

        GildedRose app = new GildedRose(items);

        for (int i = 0; i < days; i++) {
            printer.println("-------- day " + i + " --------");
            printer.println("name, sellIn, quality");
            for (Item item : items) {
                printer.println(item.toString());
            }
            printer.println("");
            app.updateQuality();
        }
    }

}
