package au.com.dius.diuscheckoutscanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import au.com.dius.diuscheckoutscanner.entities.ItemOffer;
import au.com.dius.diuscheckoutscanner.service.CheckoutScannerServiceImpl;

public class DiusCheckoutScannerApplication {

	public static void main(String[] args) {
		
		CheckoutScannerServiceImpl scanner = new CheckoutScannerServiceImpl();
		
		Map<String, Double> itemPricesList = new TreeMap<>();
		
		itemPricesList.put("atv", 109.50);
		itemPricesList.put("ipd", 549.99);
		itemPricesList.put("mpb", 1399.99);
		itemPricesList.put("vga", 30.0);
		
		// Adding the itemPrices to the itemPricesMap
		scanner.addItemPrice(itemPricesList);
		
		// If there's a offer data in a file ( something like CSV ),
		// those data can be extracted from the file and put in an arraylist
		// as following.
		List<ItemOffer> itemOffersList = new ArrayList<>();
		
		itemOffersList.add(new ItemOffer("atv", 3, 219.0));
		itemOffersList.add(new ItemOffer("ipd", 5, 2499.95));
		itemOffersList.add(new ItemOffer("mbp", 1, 1369.99));
		
		// Add the created offers to the ItemOfferDictionary
		itemOffersList.stream().forEach(item -> scanner.addItemOffer(item));
		
		scanner.addItem("atv", 3);
		scanner.addItem("vga", 1);
		System.out.println("Total Price : " + scanner.calculateTotal());
		
		scanner.emptyCart();
		
		scanner.addItem("atv", 2);
		scanner.addItem("ipd", 5);
		System.out.println("Total Price : " + scanner.calculateTotal());
		
		scanner.emptyCart();
		
		scanner.addItem("mbp", 1);
		scanner.addItem("vga", 1);
		scanner.addItem("ipd", 1);
		System.out.println("Total Price : " + scanner.calculateTotal());
		
	}
	
}
