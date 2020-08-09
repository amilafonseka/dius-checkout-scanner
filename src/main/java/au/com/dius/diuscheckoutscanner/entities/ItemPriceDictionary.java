package au.com.dius.diuscheckoutscanner.entities;

import java.util.TreeMap;

/**
 * @author amilasembunaidelage
 *
 * Map to hold the price of individual item
 * 
 */
public class ItemPriceDictionary {
	
	public static TreeMap<String, Double> itemPriceMap = 
			new TreeMap<>();
	
	static {
		itemPriceMap.put("atv", 109.50);
		itemPriceMap.put("ipd", 549.99);
		itemPriceMap.put("mpb", 1399.99);
		itemPriceMap.put("vga", 30.0);
	}

}
