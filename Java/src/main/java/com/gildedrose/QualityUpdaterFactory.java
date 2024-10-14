package com.gildedrose;

import com.gildedrose.qualityupdater.BackStagePassQualityUpdater;
import com.gildedrose.qualityupdater.ConjuredItemQualityUpdater;
import com.gildedrose.qualityupdater.ImprovingItemQualityUpdater;
import com.gildedrose.qualityupdater.LegendaryItemQualityUpdater;
import com.gildedrose.qualityupdater.NormalItemQualityUpdater;
import com.gildedrose.qualityupdater.QualityUpdater;
import com.gildedrose.service.Inventory;

public class QualityUpdaterFactory {

	
	public static QualityUpdater fetchQualityUpdater(Item item) {
		if(Inventory.isLegendary(item)) {
			return new LegendaryItemQualityUpdater();
		}
		
		if(Inventory.isImproving(item)) {
			return new ImprovingItemQualityUpdater();
		}

		if(Inventory.isConjured(item)) {
			return new ConjuredItemQualityUpdater();
		}
		
		if(Inventory.isBackStagePass(item)) {
			return new BackStagePassQualityUpdater();
		}
		
		return new NormalItemQualityUpdater();
	}

}
