package com.gildedrose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gildedrose.qualityupdater.BackStagePassQualityUpdater;
import com.gildedrose.qualityupdater.ConjuredItemQualityUpdater;
import com.gildedrose.qualityupdater.ImprovingItemQualityUpdater;
import com.gildedrose.qualityupdater.LegendaryItemQualityUpdater;
import com.gildedrose.qualityupdater.NormalItemQualityUpdater;
import com.gildedrose.qualityupdater.QualityUpdater;

public class QualityUpdaterFactory {
	// These lists could be in a service...
	private static List<String> legendaryItems = new ArrayList<>(Arrays.asList("Sulfuras, Hand of Ragnaros"));
	private static List<String> improvingItems = new ArrayList<>(Arrays.asList("Aged Brie"));
	private static List<String> conjuredItems = new ArrayList<>(Arrays.asList("Conjured Mana Cake"));
	private static List<String> backStagePasses = new ArrayList<>(Arrays.asList("Backstage passes to a TAFKAL80ETC concert"));
	
	public static QualityUpdater fetchQualityUpdater(Item item) {
		if(legendaryItems.contains(item.name)) {
			return new LegendaryItemQualityUpdater();
		}
		
		if(improvingItems.contains(item.name)) {
			return new ImprovingItemQualityUpdater();
		}

		if(conjuredItems.contains(item.name)) {
			return new ConjuredItemQualityUpdater();
		}
		
		if(backStagePasses.contains(item.name)) {
			return new BackStagePassQualityUpdater();
		}
		
		return new NormalItemQualityUpdater();
	}
	// This method should also be in that service
	public static List<String> getLegendaryItems () {
		return legendaryItems;
	}
	
	// Could add methods to aforementioned service
}
