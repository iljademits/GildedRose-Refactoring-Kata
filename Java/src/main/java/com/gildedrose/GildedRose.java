package com.gildedrose;

import com.gildedrose.qualityupdater.QualityUpdater;
import com.gildedrose.service.Inventory;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

        	if (!Inventory.isLegendary(item)) {
            	item = decreaseItemSellIn(item);
        	}
        	
        	QualityUpdater updater = QualityUpdaterFactory.fetchQualityUpdater(item);
        	
        	item = updater.updateItemQuality(item);

        }
    }
    
    private Item decreaseItemSellIn(Item item) {
    	item.sellIn -= 1;
    	return item;
    }

}