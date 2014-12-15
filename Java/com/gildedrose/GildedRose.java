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
        if (item.name.equals("Aged Brie")) {
            updateAgedBrie(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            updateBackstagePass(item);
        } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            // Do nothing
        } else {
            updateGenericItem(item);
        }
    }

    private void updateAgedBrie(final Item item) {
        increaseQuality(item);
        decreaseSellIn(item);
        if (item.sellIn < 0) {
            increaseQuality(item);
        }
    }

    private void updateBackstagePass(final Item item) {
        increaseQuality(item);

        if (item.sellIn < 11) {
            increaseQuality(item);
        }

        if (item.sellIn < 6) {
            increaseQuality(item);
        }

        decreaseSellIn(item);

        if (item.sellIn < 0) {
            zeroQuality(item);
        }
    }

    private void updateGenericItem(final Item item) {
        decreaseQuality(item);
        decreaseSellIn(item);
        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
    }

    private void decreaseSellIn(final Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void decreaseQuality(final Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
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
