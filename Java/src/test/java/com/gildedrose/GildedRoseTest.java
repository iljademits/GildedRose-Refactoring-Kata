package com.gildedrose;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    public void testAgedBrieIncreasesInQualityBy2AfterSellIn() {
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
    public void testConjuredItemsDegradeBy2() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(18, items[0].quality); // Conjured items degrade twice as fast
    }
    
    @Test
    public void testConjuredItemsDegradeBy4AfterSellIn() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(16, items[0].quality); // Conjured items degrade twice as fast
    }
    
    @Test
    public void testConjuredItemsNeverNegative() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 10, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(0, items[0].quality); // quality should not go below 0
    }
    
    @Test
    public void testConjuredItemsNeverNegativeAfterSellInPassed() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality); // quality should not go below 0
    }
    
    @Test
    public void testTextTestFixtureOutput() {
        // Redirect console output to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Run the TexttestFixture main method
        String[] args = {}; // No arguments, default days=2
        TexttestFixture.main(args);

        // Expected output after 2 days
        String expectedOutput =
                "OMGHAI!\n" +
                "-------- day 0 --------\n" +
                "name, sellIn, quality\n" +
                "+5 Dexterity Vest, 10, 20\n" +
                "Aged Brie, 2, 0\n" +
                "Elixir of the Mongoose, 5, 7\n" +
                "Sulfuras, Hand of Ragnaros, 0, 80\n" +
                "Sulfuras, Hand of Ragnaros, -1, 80\n" +
                "Backstage passes to a TAFKAL80ETC concert, 15, 20\n" +
                "Backstage passes to a TAFKAL80ETC concert, 10, 49\n" +
                "Backstage passes to a TAFKAL80ETC concert, 5, 49\n" +
                "Conjured Mana Cake, 3, 6\n" +
                "\n" +
                "-------- day 1 --------\n" +
                "name, sellIn, quality\n" +
                "+5 Dexterity Vest, 9, 19\n" +
                "Aged Brie, 1, 1\n" +
                "Elixir of the Mongoose, 4, 6\n" +
                "Sulfuras, Hand of Ragnaros, 0, 80\n" +
                "Sulfuras, Hand of Ragnaros, -1, 80\n" +
                "Backstage passes to a TAFKAL80ETC concert, 14, 21\n" +
                "Backstage passes to a TAFKAL80ETC concert, 9, 50\n" +
                "Backstage passes to a TAFKAL80ETC concert, 4, 50\n" +
                "Conjured Mana Cake, 2, 4\n" +
                "\n";

        // Get the actual output from the ByteArrayOutputStream
        String actualOutput = outputStream.toString();

        // Restore the original System.out
        System.setOut(originalOut);

        // Compare the actual output to the expected output
        assertEquals(expectedOutput, actualOutput);
    }
    
    @Test
    public void testTextTestFixtureOutput3Days() {
        // Redirect console output to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Run the TexttestFixture main method
        String[] args = {"3"}; // days=3
        TexttestFixture.main(args);

        // Expected output after 3 days
        String expectedOutput =
        	    "OMGHAI!\n" +
        	    "-------- day 0 --------\n" +
        	    "name, sellIn, quality\n" +
        	    "+5 Dexterity Vest, 10, 20\n" +
        	    "Aged Brie, 2, 0\n" +
        	    "Elixir of the Mongoose, 5, 7\n" +
        	    "Sulfuras, Hand of Ragnaros, 0, 80\n" +
        	    "Sulfuras, Hand of Ragnaros, -1, 80\n" +
        	    "Backstage passes to a TAFKAL80ETC concert, 15, 20\n" +
        	    "Backstage passes to a TAFKAL80ETC concert, 10, 49\n" +
        	    "Backstage passes to a TAFKAL80ETC concert, 5, 49\n" +
        	    "Conjured Mana Cake, 3, 6\n" +
        	    "\n" +
        	    "-------- day 1 --------\n" +
        	    "name, sellIn, quality\n" +
        	    "+5 Dexterity Vest, 9, 19\n" +
        	    "Aged Brie, 1, 1\n" +
        	    "Elixir of the Mongoose, 4, 6\n" +
        	    "Sulfuras, Hand of Ragnaros, 0, 80\n" +
        	    "Sulfuras, Hand of Ragnaros, -1, 80\n" +
        	    "Backstage passes to a TAFKAL80ETC concert, 14, 21\n" +
        	    "Backstage passes to a TAFKAL80ETC concert, 9, 50\n" +
        	    "Backstage passes to a TAFKAL80ETC concert, 4, 50\n" +
        	    "Conjured Mana Cake, 2, 4\n" +
        	    "\n" +
        	    "-------- day 2 --------\n" +
        	    "name, sellIn, quality\n" +
        	    "+5 Dexterity Vest, 8, 18\n" +
        	    "Aged Brie, 0, 2\n" +
        	    "Elixir of the Mongoose, 3, 5\n" +
        	    "Sulfuras, Hand of Ragnaros, 0, 80\n" +
        	    "Sulfuras, Hand of Ragnaros, -1, 80\n" +
        	    "Backstage passes to a TAFKAL80ETC concert, 13, 22\n" +
        	    "Backstage passes to a TAFKAL80ETC concert, 8, 50\n" +
        	    "Backstage passes to a TAFKAL80ETC concert, 3, 50\n" +
        	    "Conjured Mana Cake, 1, 2\n" +
        	    "\n" +
        	    "-------- day 3 --------\n" +
        	    "name, sellIn, quality\n" +
        	    "+5 Dexterity Vest, 7, 17\n" +
        	    "Aged Brie, -1, 4\n" +
        	    "Elixir of the Mongoose, 2, 4\n" +
        	    "Sulfuras, Hand of Ragnaros, 0, 80\n" +
        	    "Sulfuras, Hand of Ragnaros, -1, 80\n" +
        	    "Backstage passes to a TAFKAL80ETC concert, 12, 23\n" +
        	    "Backstage passes to a TAFKAL80ETC concert, 7, 50\n" +
        	    "Backstage passes to a TAFKAL80ETC concert, 2, 50\n" +
        	    "Conjured Mana Cake, 0, 0\n" +
        	    "\n";


        // Get the actual output from the ByteArrayOutputStream
        String actualOutput = outputStream.toString();

        // Restore the original System.out
        System.setOut(originalOut);

        // Compare the actual output to the expected output
        assertEquals(expectedOutput, actualOutput);
    }

}
