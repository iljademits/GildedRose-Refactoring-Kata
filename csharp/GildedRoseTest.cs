using NUnit.Framework;
using System.Collections.Generic;

namespace csharp
{
    [TestFixture]
    public class GildedRoseTest
    {
        [Test]
        public void NameDoesNotChange()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "foo", SellIn = 0, Quality = 0 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual("foo", Items[0].Name);
        }

        [Test]
        public void SellInValueDecreasesByOne()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "foo", SellIn = 10, Quality = 10 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(9, Items[0].SellIn);
        }

        [Test]
        public void SellInValueDecreasesToNegativeValue()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "foo", SellIn = 10, Quality = 10 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(9, Items[0].SellIn);
        }

        [Test]
        public void SulfurasSellInValueDoesNotChange()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "Sulfuras, Hand of Ragnaros", SellIn = 10, Quality = 80 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(10, Items[0].SellIn);
        }

        [Test]
        public void QualityDecreasesByOneBeforeSellByDate()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "foo", SellIn = 10, Quality = 10 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(9, Items[0].Quality);
        }

        [Test]
        public void QualityDecreasesByTwoAfterSellByDate()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "foo", SellIn = 0, Quality = 10 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(8, Items[0].Quality);
        }

        [Test]
        public void QualityDoesNotDecreaseBelowZeroBeforeSellByDate()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "foo", SellIn = 10, Quality = 0 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(0, Items[0].Quality);
        }

        [Test]
        public void QualityDoesNotDecreaseBelowZeroAfterSellByDate()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "foo", SellIn = 0, Quality = 0 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(0, Items[0].Quality);
        }

        [Test]
        public void BrieQualityIncreasesByOneBeforeSellByDate()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "Aged Brie", SellIn = 10, Quality = 0 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(1, Items[0].Quality);
        }

        /* Requirements on "Aged Brie" are somewhat unclear. 
         * It does not say it should increases twice as fast, only that a 'default' item degrades twice as fast.
         * I am going to suppose the existing code is meeting the requirements. */
        [Test]
        public void BrieQualityIncreasesByTwoAfterSellByDate()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "Aged Brie", SellIn = -1, Quality = 0 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(2, Items[0].Quality);
        }

        [Test]
        public void BrieQualityDoesNotIncreaseAbove50BeforeSellByDate()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "Aged Brie", SellIn = 10, Quality = 50 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(50, Items[0].Quality);
        }

        [Test]
        public void BrieQualityDoesNotIncreaseAbove50AfterSellByDate()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "Aged Brie", SellIn = 0, Quality = 50 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(50, Items[0].Quality);
        }

        [Test]
        public void SulfurasQualityDoesNotChange()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "Sulfuras, Hand of Ragnaros", SellIn = 10, Quality = 80 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(80, Items[0].Quality);
        }

        [Test]
        public void BackstagePassQualityIncreasesByTwoWithinTenDaysOffConcert()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "Backstage passes to a TAFKAL80ETC concert", SellIn = 10, Quality = 20 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(22, Items[0].Quality);
        }

        [Test]
        public void BackstagePassQualityIncreasesByThreeWithin5DaysOffConcert()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "Backstage passes to a TAFKAL80ETC concert", SellIn = 5, Quality = 20 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(23, Items[0].Quality);
        }

        [Test]
        public void BackstagePassQualityDropsToZeroAfterConcert()
        {
            IList<Item> Items = new List<Item> { new Item { Name = "Backstage passes to a TAFKAL80ETC concert", SellIn = 0, Quality = 20 } };
            GildedRose app = new GildedRose(Items);
            app.UpdateQuality();
            Assert.AreEqual(0, Items[0].Quality);
        }
    }
}
