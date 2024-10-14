package com.gildedrose.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gildedrose.Item;

public class Inventory {
	
	private static List<String> legendaryItems = new ArrayList<>(Arrays.asList("Sulfuras, Hand of Ragnaros"));
	private static List<String> improvingItems = new ArrayList<>(Arrays.asList("Aged Brie"));
	private static List<String> conjuredItems = new ArrayList<>(Arrays.asList("Conjured Mana Cake"));
	private static List<String> backStagePasses = new ArrayList<>(Arrays.asList("Backstage passes to a TAFKAL80ETC concert"));
	
	public static boolean isLegendary(Item item) {
		return legendaryItems.contains(item.name);
	}
	
	public static boolean isImproving(Item item) {
		return improvingItems.contains(item.name);
	}
	
	public static boolean isConjured(Item item) {
		return conjuredItems.contains(item.name);
	}
	
	public static boolean isBackStagePass(Item item) {
		return backStagePasses.contains(item.name);
	}
	
	// If needed CRUD operations for inventory could be added

}
