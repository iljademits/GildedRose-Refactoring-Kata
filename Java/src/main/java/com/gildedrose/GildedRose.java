package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
        	
            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
            	items[i] = decreaseItemSellIn(items[i]);
            }
            
            if (items[i].name.equals("Aged Brie")) {
            	items[i] = updateAgedBrieQuality(items[i]);
            } else if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            	items[i] = updateBackstagePassQuality(items[i]);
            } else if (items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
            	// DO NOTHING
            } else {
            	items[i] = updateNormalItemQuality(items[i]);
            }
        }
    }
    
    private Item decreaseItemSellIn(Item item) {
    	item.sellIn -= 1;
    	return item;
    }
    
    private Item decreaseItemQualityBy(int amount, Item item) {
    	if (item.quality > amount) {
    		item.quality -= amount;
        	return item;
    	}
    	item.quality = 0;
    	return item;
    }
    
    private Item increaseItemQualityBy(int amount, Item item) {
    	if (item.quality + amount < 50) {
    		item.quality += amount;
        	return item;
    	}
    	item.quality = 50;
    	return item;
    }
    
    private Item setItemQualityToZero(Item item) {
    	item.quality = 0;
    	return item;
    }
    
    private Item updateAgedBrieQuality(Item brie) {
        // This would mean Aged Brie increases twice as fast after sellIn has passed
        if (brie.sellIn < 0) {
        	return increaseItemQualityBy(2, brie);
        }
    	return increaseItemQualityBy(1, brie);
    }
    
    private Item updateBackstagePassQuality(Item backstagePass) {
        if (backstagePass.sellIn < 0) {
        	return setItemQualityToZero(backstagePass);
        }
        
        if (backstagePass.sellIn < 5) {
        	return increaseItemQualityBy(3, backstagePass);
        }
        
        if (backstagePass.sellIn < 10) {
        	return increaseItemQualityBy(2, backstagePass);
        }
        
        return increaseItemQualityBy(1, backstagePass);
    }
    
    private Item updateNormalItemQuality(Item item) {
    	if (item.sellIn < 0) {
    		return decreaseItemQualityBy(2, item);
    	}
    	return decreaseItemQualityBy(1, item);
    }
}
