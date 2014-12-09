package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GildedRoseTest {

	@Test
	public void legendaryItemDoesNotHaveToBeSold() throws Exception {
		final Item[] items = new Item[] { legendaryItem() };
		updateQuality(items);
		assertEquals(20, items[0].sellIn);
	}

	@Test
	public void notLegendaryItemHasToBeSold() throws Exception {
		final Item[] items = new Item[] { genericItem(0), agedBrie(0),
				backstagePass(20, 0) };
		updateQuality(items);
		assertEquals(19, items[0].sellIn);
		assertEquals(19, items[1].sellIn);
		assertEquals(19, items[2].sellIn);
	}

	@Test
	public void qualityDoesNotGoBelowZeroForGenericItem() throws Exception {
		final Item[] items = new Item[] { genericItem(0) };
		updateQuality(items);
		assertEquals(0, items[0].quality);
	}

	@Test
	public void qualityDoesNotChangeForLegendaryItem() throws Exception {
		final Item[] items = new Item[] { legendaryItem() };
		updateQuality(items);
		assertEquals(10, items[0].quality);
	}

	@Test
	public void qualityDecreasesByOneForGenericItem() throws Exception {
		final Item[] items = new Item[] { genericItem(10) };
		updateQuality(items);
		assertEquals(9, items[0].quality);
	}

	@Test
	public void qualityDoesNotGoAboveFifty() throws Exception {
		final Item[] items = new Item[] { agedBrie(50),
				backstagePass(20, 50) };
		updateQuality(items);
		assertEquals(50, items[0].quality);
		assertEquals(50, items[1].quality);
	}

	@Test
	public void qualityIncreasesByOneForAgedBrie() throws Exception {
		final Item[] items = new Item[] { agedBrie(10) };
		updateQuality(items);
		assertEquals(11, items[0].quality);
	}

	@Test
	public void qualityIncreasesByOneForBackstageWhenSellInGreaterOrEqualsEleven()
			throws Exception {
		final Item[] items = new Item[] { backstagePass(11, 10) };
		updateQuality(items);
		assertEquals(11, items[0].quality);
	}

	@Test
	public void qualityIncreasesByTwoForBackstageWhenSellInGreaterOrEqualsSixAndLesserThanEleven()
			throws Exception {
		final Item[] items = new Item[] { backstagePass(10, 10) };
		updateQuality(items);
		assertEquals(12, items[0].quality);
	}

	@Test
	public void qualityDoesNotGoAboveFifityForBackstageWhenSellInGreaterOrEqualsSixAndLesserThanEleven()
			throws Exception {
		final Item[] items = new Item[] { backstagePass(10, 49) };
		updateQuality(items);
		assertEquals(50, items[0].quality);
	}

	@Test
	public void qualityIncreasesByThreeForBackstageWhenSellInGreaterOrEqualsZeroAndLesserThanSix()
			throws Exception {
		final Item[] items = new Item[] { backstagePass(5, 10) };
		updateQuality(items);
		assertEquals(13, items[0].quality);
	}

	@Test
	public void qualityDoesNotGoAboveFifityForBackstageWhenSellInGreaterOrEqualsZeroAndLesserThanSix()
			throws Exception {
		final Item[] items = new Item[] { backstagePass(5, 48) };
		updateQuality(items);
		assertEquals(50, items[0].quality);
	}

	@Test
	public void qualityIncreasesByTwoForOverdueAgedBrie() throws Exception {
		final Item[] items = new Item[] { overdueAgedBrie(10) };
		updateQuality(items);
		assertEquals(12, items[0].quality);
	}

	@Test
	public void qualityDoesNotGoAboveFiftyForOverdueAgedBrie() throws Exception {
		final Item[] items = new Item[] { overdueAgedBrie(50) };
		updateQuality(items);
		assertEquals(50, items[0].quality);
	}

	@Test
	public void qualityDropsToZeroForOverdueBackstagePass() throws Exception {
		final Item[] items = new Item[] { overdueBackstagePass() };
		updateQuality(items);
		assertEquals(0, items[0].quality);
	}

	@Test
	public void qualityDoesNotChangeForOverdueLegendaryItem() throws Exception {
		final Item[] items = new Item[] { overdueLegendaryItem() };
		updateQuality(items);
		assertEquals(10, items[0].quality);
	}

	@Test
	public void qualityDecreasesByTwoForOverdueGenericItem() throws Exception {
		final Item[] items = new Item[] { overdueGenericItem(10) };
		updateQuality(items);
		assertEquals(8, items[0].quality);
	}

	@Test
	public void qualityDoesNotGoBelowZeroForOverdueGenericItem()
			throws Exception {
		final Item[] items = new Item[] { overdueGenericItem(0) };
		updateQuality(items);
		assertEquals(0, items[0].quality);
	}

	private GildedRose updateQuality(final Item[] items) {
		final GildedRose app = new GildedRose(items);
		app.updateQuality();
		return app;
	}

	private Item backstagePass(final int sellIn, final int quality) {
		return new Item("Backstage passes to a TAFKAL80ETC concert", sellIn,
				quality);
	}

	private Item overdueBackstagePass() {
		return backstagePass(-1, 10);
	}

	private Item legendaryItem() {
		return new Item("Sulfuras, Hand of Ragnaros", 20, 10);
	}

	private Item overdueLegendaryItem() {
		return new Item("Sulfuras, Hand of Ragnaros", -1, 10);
	}

	private Item genericItem(final int quality) {
		return new Item("generic item", 20, quality);
	}

	private Item overdueGenericItem(final int quality) {
		return new Item("generic item", -1, quality);
	}

	private Item agedBrie(final int quality) {
		return new Item("Aged Brie", 20, quality);
	}

	private Item overdueAgedBrie(final int quality) {
		return new Item("Aged Brie", -1, quality);
	}
}
