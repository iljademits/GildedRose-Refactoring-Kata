package com.gildedrose.qualityupdater;

import com.gildedrose.Item;

// Not really needed, but makes it clearer and its behaviour might change
public class LegendaryItemQualityUpdater implements QualityUpdater {

	@Override
	public Item updateItemQuality(Item legendaryItem) {
    	return legendaryItem;
	}

}
