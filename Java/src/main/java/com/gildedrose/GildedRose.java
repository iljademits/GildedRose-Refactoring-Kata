package com.gildedrose;

import com.gildedrose.qualityupdater.QualityUpdater;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

        	if (QualityUpdaterFactory.getLegendaryItems().contains(item.name)) {
        		continue;
        	}
        	
        	item = decreaseItemSellIn(item);
        	
        	QualityUpdater updater = QualityUpdaterFactory.fetchQualityUpdater(item);
        	
        	item = updater.updateItemQuality(item);

        }
    }
    
    private Item decreaseItemSellIn(Item item) {
    	item.sellIn -= 1;
    	return item;
    }

}