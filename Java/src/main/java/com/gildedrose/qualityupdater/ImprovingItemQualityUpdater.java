package com.gildedrose.qualityupdater;

import com.gildedrose.Item;

public class ImprovingItemQualityUpdater implements QualityUpdater {
	
    private final static int QUALITY_UPPER_LIMIT = 50;

	@Override
	public Item updateItemQuality(Item improvingItem) {
      // This would mean Aged Brie increases twice as fast after sellIn has passed
      if (improvingItem.sellIn < 0) {
      	return increaseItemQualityBy(2, improvingItem);
      }
  	return increaseItemQualityBy(1, improvingItem);
	}
	
    private Item increaseItemQualityBy(int qualityIncrement, Item item) {
    	if (item.quality + qualityIncrement < QUALITY_UPPER_LIMIT) {
    		item.quality += qualityIncrement;
        	return item;
    	}
    	item.quality = QUALITY_UPPER_LIMIT;
    	return item;
    }

}
