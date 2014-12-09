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
		final Item[] items = new Item[] { genericItemNotOverdueWithQuality(0) };
		updateQuality(items);
		assertEquals(19, items[0].sellIn);
	}

	@Test
	public void qualityDoesNotGoBelowZero() throws Exception {
		final Item[] items = new Item[] { genericItemNotOverdueWithQuality(0) };
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
		final Item[] items = new Item[] { genericItemNotOverdueWithQuality(10) };
		updateQuality(items);
		assertEquals(9, items[0].quality);
	}

	private GildedRose updateQuality(final Item[] items) {
		final GildedRose app = new GildedRose(items);
		app.updateQuality();
		return app;
	}

	private Item legendaryItem() {
		return new Item("Sulfuras, Hand of Ragnaros", 20, 10);
	}

	private Item genericItemNotOverdueWithQuality(final int quality) {
		return new Item("generic item", 20, quality);
	}
}
