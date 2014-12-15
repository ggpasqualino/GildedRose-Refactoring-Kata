package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(final Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (final Item item : items) {
            updateQuality(item);
        }
    }

    private void updateQuality(final Item item) {
        if (item.name.equals("Aged Brie")
                || item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        increaseQuality(item);
                    }

                    if (item.sellIn < 6) {
                        increaseQuality(item);
                    }
                }
            }
        } else {
            decreaseQuality(item);
        }

        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            // Do nothing
        } else {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (item.name.equals("Aged Brie")) {
                increaseQuality(item);
            } else {
                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    zeroQuality(item);
                } else {
                    decreaseQuality(item);
                }
            }
        }
    }

    private void decreaseQuality(final Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }
        }
    }

    private void increaseQuality(final Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void zeroQuality(final Item item) {
        item.quality = item.quality - item.quality;
    }
}
