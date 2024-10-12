package com.gildedrose;

class GildedRose {
    Item[] items;
    
    private final static int QUALITY_UPPER_LIMIT = 50;
    private final static int QUALITY_LOWER_LIMIT = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
        	
        	if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
        		continue;
        	}
        	
        	item = decreaseItemSellIn(item);
            
            if (item.name.equals("Aged Brie")) {
            	item = updateAgedBrieQuality(item);
            } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            	item = updateBackstagePassQuality(item);
            } else if (item.name.equals("Conjured Mana Cake")) {
            	item = updateConjuredItemQuality(item);
            } else {
            	item = updateNormalItemQuality(item);
            }
        }
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
    
    private Item updateConjuredItemQuality(Item conjuredItem) {
    	if (conjuredItem.sellIn < 0) {
    		return decreaseItemQualityBy(4, conjuredItem);
    	}
    	return decreaseItemQualityBy(2, conjuredItem);
    }
    
    private Item updateNormalItemQuality(Item normalItem) {
    	if (normalItem.sellIn < 0) {
    		return decreaseItemQualityBy(2, normalItem);
    	}
    	return decreaseItemQualityBy(1, normalItem);
    }
    
    
    private Item decreaseItemSellIn(Item item) {
    	item.sellIn -= 1;
    	return item;
    }
    
    private Item decreaseItemQualityBy(int qualityDecrement, Item item) {
    	if (item.quality - qualityDecrement > QUALITY_LOWER_LIMIT) {
    		item.quality -= qualityDecrement;
        	return item;
    	}
    	item.quality = QUALITY_LOWER_LIMIT;
    	return item;
    }
    
    private Item increaseItemQualityBy(int qualityIncrement, Item item) {
    	if (item.quality + qualityIncrement < QUALITY_UPPER_LIMIT) {
    		item.quality += qualityIncrement;
        	return item;
    	}
    	item.quality = QUALITY_UPPER_LIMIT;
    	return item;
    }
    
    private Item setItemQualityToZero(Item item) {
    	item.quality = 0;
    	return item;
    }
    

}
