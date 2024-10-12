package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    public void testNormalItemDecreasesQualityAndSellIn() {
        Item[] items = new Item[] { new Item("normal", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);  // sellIn should decrease by 1
        assertEquals(19, items[0].quality); // quality should decrease by 1
    }

    @Test
    public void testQualityNeverNegative() {
        Item[] items = new Item[] { new Item("normal", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(0, items[0].quality); // quality should not go below 0
    }

    @Test
    public void testExpiredNormalItemDecreasesQualityTwiceAsFast() {
        Item[] items = new Item[] { new Item("normal", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(8, items[0].quality); // after sellIn date, quality degrades twice as fast
    }
    
    @Test
    public void testExpiredNormalItemDecreasesQualityTwiceAsFastNeverNegative() {
        Item[] items = new Item[] { new Item("normal", 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality); // after sellIn date, quality degrades twice as fast but should not go below 0
    }
    
    @Test
    public void testAgedBrieIncreasesInQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);  // sellIn should decrease by 1
        assertEquals(31, items[0].quality); // Aged Brie increases in quality
    }
    
    @Test
    public void testAgedBrieIncreasesInQualityTwiceAsFastAfterSellIn() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);  // sellIn should decrease by 1
        assertEquals(32, items[0].quality); // Aged Brie increases in quality by 2
    }

    @Test
    public void testAgedBrieQualityDoesNotExceed50() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(50, items[0].quality); // Quality should not exceed 50
    }
    
    @Test
    public void testAgedBrieQualityDoesNotExceed50AfterSellInPassed() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(50, items[0].quality); // Quality should not exceed 50
    }

    @Test
    public void testSulfurasNeverDecreasesInQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, items[0].sellIn);  // sellIn does not change for Sulfuras
        assertEquals(80, items[0].quality); // Sulfuras quality does not change
    }

    @Test
    public void testBackstagePassesIncreaseInQualityBeforeConcert() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(14, items[0].sellIn);
        assertEquals(21, items[0].quality); // quality increases by 1
    }

    @Test
    public void testBackstagePassesIncreaseInQualityBy2When10DaysLeft() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(22, items[0].quality); // quality increases by 2 when 10 days left
    }

    @Test
    public void testBackstagePassesIncreaseInQualityBy3When5DaysLeft() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(23, items[0].quality); // quality increases by 3 when 5 days left
    }

    @Test
    public void testBackstagePassesDropToZeroAfterConcert() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality); // quality drops to 0 after concert
    }
    
    @Test
    public void testQualityDoesNotExceed50() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(50, items[0].quality); // quality cannot go above 50
    }

    @Test
    public void testConjuredItemsDegradeTwiceAsFast() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(18, items[0].quality); // Conjured items degrade twice as fast
    }
    
    @Test
    public void testConjuredItemsNeverNegative() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 10, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(0, items[0].quality); // Conjured items degrade twice as fast
    }
    
    @Test
    public void testConjuredItemsNeverNegativeAfterSellInPassed() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(0, items[0].quality); // Conjured items degrade twice as fast
    }

}
