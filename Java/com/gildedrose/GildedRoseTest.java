package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GildedRoseTest {

    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String LEGENDARY_ITEM = "Sulfuras, Hand of Ragnaros";
    private static final String GENERIC_ITEM = "generic item";
    private static final String AGED_BRIE = "Aged Brie";
    private static final int SELL_IN = 20;
    private static final int SELL_IN_OVERDUE = -1;
    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;
    private static final int DEFAULT_QUALITY = 10;

    @Test
    public void legendaryItemDoesNotHaveToBeSold() {
        final Item legendaryItem = legendaryItem();
        updateQuality(legendaryItem);
        assertEquals(SELL_IN, legendaryItem.sellIn);
    }

    @Test
    public void notLegendaryItemHasToBeSold() {
        final Item genericItem = genericItem(MIN_QUALITY);
        final Item agedBrie = agedBrie(MIN_QUALITY);
        final Item backstagePass = backstagePass(SELL_IN, MIN_QUALITY);

        updateQuality(genericItem, agedBrie, backstagePass);

        final int expectedSellIn = SELL_IN - 1;
        assertEquals(expectedSellIn, genericItem.sellIn);
        assertEquals(expectedSellIn, agedBrie.sellIn);
        assertEquals(expectedSellIn, backstagePass.sellIn);
    }

    @Test
    public void qualityDoesNotGoBelowZeroForGenericItem() {
        final Item genericItem = genericItem(MIN_QUALITY);
        updateQuality(genericItem);
        assertEquals(MIN_QUALITY, genericItem.quality);
    }

    @Test
    public void qualityDoesNotChangeForLegendaryItem() {
        final Item legendaryItem = legendaryItem();
        updateQuality(legendaryItem);
        assertEquals(DEFAULT_QUALITY, legendaryItem.quality);
    }

    @Test
    public void qualityDecreasesByOneForGenericItem() {
        final Item genericItem = genericItem(DEFAULT_QUALITY);
        updateQuality(genericItem);
        final int expectedQuality = DEFAULT_QUALITY - 1;
        assertEquals(expectedQuality, genericItem.quality);
    }

    @Test
    public void qualityDoesNotGoAboveFifty() {
        final Item agedBrie = agedBrie(MAX_QUALITY);
        final Item backstagePass = backstagePass(SELL_IN, MAX_QUALITY);

        updateQuality(agedBrie, backstagePass);

        assertEquals(MAX_QUALITY, agedBrie.quality);
        assertEquals(MAX_QUALITY, backstagePass.quality);
    }

    @Test
    public void qualityIncreasesByOneForAgedBrie() {
        final Item agedBrie = agedBrie(DEFAULT_QUALITY);
        updateQuality(agedBrie);
        final int expectedQuality = DEFAULT_QUALITY + 1;
        assertEquals(expectedQuality, agedBrie.quality);
    }

    @Test
    public void qualityIncreasesByOneForBackstageWhenSellInGreaterOrEqualsEleven() {
        final Item backstagePass = backstagePass(11, DEFAULT_QUALITY);
        updateQuality(backstagePass);
        final int expectedQuality = DEFAULT_QUALITY + 1;
        assertEquals(expectedQuality, backstagePass.quality);
    }

    @Test
    public void qualityIncreasesByTwoForBackstageWhenSellInGreaterOrEqualsSixAndLesserThanEleven() {
        final Item backstagePass = backstagePass(10, DEFAULT_QUALITY);
        updateQuality(backstagePass);
        final int expectedQuality = DEFAULT_QUALITY + 2;
        assertEquals(expectedQuality, backstagePass.quality);
    }

    @Test
    public void qualityDoesNotGoAboveFifityForBackstageWhenSellInGreaterOrEqualsSixAndLesserThanEleven() {
        final Item backstagePass = backstagePass(10, 49);
        updateQuality(backstagePass);
        assertEquals(MAX_QUALITY, backstagePass.quality);
    }

    @Test
    public void qualityIncreasesByThreeForBackstageWhenSellInGreaterOrEqualsZeroAndLesserThanSix() {
        final Item backstagePass = backstagePass(5, DEFAULT_QUALITY);
        updateQuality(backstagePass);
        final int expectedQuality = DEFAULT_QUALITY + 3;
        assertEquals(expectedQuality, backstagePass.quality);
    }

    @Test
    public void qualityDoesNotGoAboveFifityForBackstageWhenSellInGreaterOrEqualsZeroAndLesserThanSix() {
        final Item backstagePass = backstagePass(5, 48);
        updateQuality(backstagePass);
        assertEquals(MAX_QUALITY, backstagePass.quality);
    }

    @Test
    public void qualityIncreasesByTwoForOverdueAgedBrie() {
        final Item overdueAgedBrie = overdueAgedBrie(DEFAULT_QUALITY);
        updateQuality(overdueAgedBrie);
        final int expectedQuality = DEFAULT_QUALITY + 2;
        assertEquals(expectedQuality, overdueAgedBrie.quality);
    }

    @Test
    public void qualityDoesNotGoAboveFiftyForOverdueAgedBrie() {
        final Item overdueAgedBrie = overdueAgedBrie(MAX_QUALITY);
        updateQuality(overdueAgedBrie);
        assertEquals(MAX_QUALITY, overdueAgedBrie.quality);
    }

    @Test
    public void qualityDropsToZeroForOverdueBackstagePass() {
        final Item overdueBackstagePass = overdueBackstagePass();
        updateQuality(overdueBackstagePass);
        assertEquals(MIN_QUALITY, overdueBackstagePass.quality);
    }

    @Test
    public void qualityDoesNotChangeForOverdueLegendaryItem() {
        final Item overdueLegendaryItem = overdueLegendaryItem();
        updateQuality(overdueLegendaryItem);
        assertEquals(DEFAULT_QUALITY, overdueLegendaryItem.quality);
    }

    @Test
    public void qualityDecreasesByTwoForOverdueGenericItem() {
        final Item overdueGenericItem = overdueGenericItem(DEFAULT_QUALITY);
        updateQuality(overdueGenericItem);
        final int expectedQuality = DEFAULT_QUALITY - 2;
        assertEquals(expectedQuality, overdueGenericItem.quality);
    }

    @Test
    public void qualityDoesNotGoBelowZeroForOverdueGenericItem() {
        final Item overdueGenericItem = overdueGenericItem(MIN_QUALITY);
        updateQuality(overdueGenericItem);
        assertEquals(MIN_QUALITY, overdueGenericItem.quality);
    }

    private void updateQuality(final Item... items) {
        final GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    private Item backstagePass(final int sellIn, final int quality) {
        return new Item(BACKSTAGE_PASS, sellIn, quality);
    }

    private Item overdueBackstagePass() {
        return backstagePass(SELL_IN_OVERDUE, DEFAULT_QUALITY);
    }

    private Item legendaryItem() {
        return new Item(LEGENDARY_ITEM, SELL_IN, DEFAULT_QUALITY);
    }

    private Item overdueLegendaryItem() {
        return new Item(LEGENDARY_ITEM, SELL_IN_OVERDUE, DEFAULT_QUALITY);
    }

    private Item genericItem(final int quality) {
        return new Item(GENERIC_ITEM, SELL_IN, quality);
    }

    private Item overdueGenericItem(final int quality) {
        return new Item(GENERIC_ITEM, SELL_IN_OVERDUE, quality);
    }

    private Item agedBrie(final int quality) {
        return new Item(AGED_BRIE, SELL_IN, quality);
    }

    private Item overdueAgedBrie(final int quality) {
        return new Item(AGED_BRIE, SELL_IN_OVERDUE, quality);
    }
}
