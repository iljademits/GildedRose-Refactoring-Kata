package com.gildedrose.qualityupdater;

import com.gildedrose.Item;

public class BackStagePassQualityUpdater implements QualityUpdater {
	
    private final static int QUALITY_UPPER_LIMIT = 50;

	@Override
	public Item updateItemQuality(Item backstagePass) {
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
