package com.gildedrose.qualityupdater;

import com.gildedrose.Item;

public class NormalItemQualityUpdater implements QualityUpdater {
	
    private final static int QUALITY_LOWER_LIMIT = 0;

	@Override
	public Item updateItemQuality(Item normalItem) {
    	if (normalItem.sellIn < 0) {
    		return decreaseItemQualityBy(2, normalItem);
    	}
    	return decreaseItemQualityBy(1, normalItem);
	}
	
    private Item decreaseItemQualityBy(int qualityDecrement, Item item) {
    	if (item.quality - qualityDecrement > QUALITY_LOWER_LIMIT) {
    		item.quality -= qualityDecrement;
        	return item;
    	}
    	item.quality = QUALITY_LOWER_LIMIT;
    	return item;
    }

}
