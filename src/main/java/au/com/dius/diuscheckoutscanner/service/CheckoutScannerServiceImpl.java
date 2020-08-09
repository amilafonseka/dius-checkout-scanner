package au.com.dius.diuscheckoutscanner.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import au.com.dius.diuscheckoutscanner.entities.ItemOffer;
import au.com.dius.diuscheckoutscanner.entities.ItemOfferDictionary;
import au.com.dius.diuscheckoutscanner.entities.ItemPriceDictionary;
import au.com.dius.diuscheckoutscanner.exception.NoSuchItemException;
import au.com.dius.diuscheckoutscanner.exception.QuantityDeficiencyException;

public class CheckoutScannerServiceImpl implements ICheckoutScannerService {

	public static TreeMap<String, Integer> itemsListMap = 
			new TreeMap<>();
	
	/**
	 *
	 * Method to calculate all total price based on the contents of the list
	 *
	 */
	@Override
	public double calculateTotal() {
		
		Double total = 0.0;
		
		// Iterate through the itemListMap and calculate the total value of each item 
		// and then add the total
		Set<String> keys = itemsListMap.keySet();
		
		for(Iterator<String> i = keys.iterator(); i.hasNext();) {
			
			String item = i.next();
			
			Integer quantity = itemsListMap.get(item);
			
			// Check if there is an offer for this item and process the price
			// accordingly
			if(ItemOfferDictionary.itemOfferMap.containsKey(item)) {
				
				ItemOffer thisOffer = ItemOfferDictionary.itemOfferMap.get(item);
				
				// If the quantity of the item in the itemListMap is more than the
				// offer quantity, then process the items and their prices accordingly
				if(thisOffer.getQuantity() < quantity) {
					
					total += (quantity % thisOffer.getQuantity()) * ItemPriceDictionary
							.itemPriceMap.get(item) + (quantity / thisOffer.getQuantity())
							* thisOffer.getPrice();
					
				}else if(thisOffer.getQuantity() == quantity) {
					
					total += thisOffer.getPrice();
					
				}else {
					
					total += (quantity) * ItemPriceDictionary.itemPriceMap.get(item);
					
				}
			} else {
				
				total += (quantity) * ItemPriceDictionary.itemPriceMap.get(item);
				
			}
			
		}
		
		return total;
	}
	
	
	/**
	 * Add a new offer to the ItemOfferDictionary
	 * 
	 * @param offer
	 */
	public void addItemOffer(ItemOffer offer) {
		
		if(ItemOfferDictionary.itemOfferMap.containsKey(offer.getName())){
			
			ItemOfferDictionary.itemOfferMap.remove(offer.getName());
			
			ItemOfferDictionary.itemOfferMap.put(offer.getName(), offer);
			
		}else {
			
			ItemOfferDictionary.itemOfferMap.put(offer.getName(), offer);
			
		}
		
	}
	
	/**
	 * 
	 * Clear the Item Offer Map
	 * 
	 */
	public void clearItemOfferMap() {
		
		ItemOfferDictionary.itemOfferMap.clear();
		
	}
	
	/**
	 * Add a new price to the ItemPriceDictionary
	 * 
	 * @param offer
	 */
	public void addItemPrice(Map<String, Double> itemPriceMap) {
		
		ItemPriceDictionary.itemPriceMap.putAll(itemPriceMap);
		
	}
	
	/**
	 * 
	 * Clear the Item Offer Map
	 * 
	 */
	public void clearItemPriceMap() {
		
		ItemPriceDictionary.itemPriceMap.clear();
		
	}

	/**
	 *
	 * Method to add item to the existing list
	 *
	 */
	@Override
	public void addItem(String item, Integer quantity) {
		
		// If the item does not exists in the list, then add it
		if(!itemsListMap.containsKey(item))
			itemsListMap.put(item, quantity);
		else {
			
			// If the list already contains the item then just update
			// the amount by adding the new quantity to old one
			itemsListMap.put(item, quantity + itemsListMap.get(item));
			
		}
		
	}

	/**
	 *
	 * Method to remove items along with their quantities from the existing list
	 * @throws NoSuchItemException 
	 * @throws QuantityDeficiencyException 
	 *
	 */
	@Override
	public void removeItem(String item, Integer quantity) throws NoSuchItemException, QuantityDeficiencyException {
		
		//If the item is not in the list then throw Exception
		if(!itemsListMap.containsKey(item))
			throw new NoSuchItemException("No such item in your cart");
		
		// If the item's quantity to be removed, is equal to the existing quantity
		// of the item, then remove the item from the list OR else update the
		// quantity of the item in the list. If the quantity to be removed is more
		// than what's existing in the list, then throw an exception as well.
		if(itemsListMap.get(item) == quantity)
			itemsListMap.remove(item);
		else if(itemsListMap.get(item) > quantity)
			itemsListMap.put(item, itemsListMap.get(item) - quantity);
		else
			throw new QuantityDeficiencyException("Quantity for the provided"
					+ " item is more than the quantity in the cart");
	}

	@Override
	public void emptyCart() {
		
		itemsListMap.clear();
		
	}

}
