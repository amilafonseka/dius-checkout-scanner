package au.com.dius.diuscheckoutscanner.service;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.com.dius.diuscheckoutscanner.entities.ItemOffer;
import au.com.dius.diuscheckoutscanner.exception.NoSuchItemException;
import au.com.dius.diuscheckoutscanner.exception.QuantityDeficiencyException;

/**
 * This class contains all test cases for testing
 * {@link au.com.dius.diuscheckoutscanner.service.CheckoutScannerServiceImpl}
 * 
 * @author amilasembunaidelage
 *
 */
public class CheckoutScannerServiceImplTest {

	CheckoutScannerServiceImpl checkoutScanner = new CheckoutScannerServiceImpl();
	
	@Test
	public void testCalculatePriceWhenNoOffers() {
		
		checkoutScanner.addItem("atv", 3);
		checkoutScanner.addItem("vga", 1);
		
		assertEquals(358.5, checkoutScanner.calculateTotal(), 0.0);
		
		checkoutScanner.emptyCart();
		
	}
	
	@Test
	public void testNoSuchItemException() {
		
		checkoutScanner.addItem("atv", 3);
		checkoutScanner.addItem("vga", 1);
		
		Throwable exception = assertThrows(NoSuchItemException.class,
				() -> checkoutScanner.removeItem("invalidItem", 1));
		
		assertEquals("No such item in your cart", exception.getMessage());
		
		checkoutScanner.emptyCart();
		
	}
	
	@Test
	public void testQuantityDeficiencyException() {
		
		checkoutScanner.addItem("atv", 3);
		checkoutScanner.addItem("vga", 1);
		
		Throwable exception = assertThrows(QuantityDeficiencyException.class,
				() -> checkoutScanner.removeItem("atv", 4));
		
		assertEquals("Quantity for the provided item is more than the quantity in the cart", 
				exception.getMessage());
		
		checkoutScanner.emptyCart();
		
	}
	
	@Test
	public void testOneOffer() {
		
		List<ItemOffer> itemOffersList = new ArrayList<>();
		
		itemOffersList.add(new ItemOffer("atv", 3, 219.0));
		
		itemOffersList.stream().forEach(item -> checkoutScanner.addItemOffer(item));
		
		checkoutScanner.addItem("atv", 3);
		checkoutScanner.addItem("vga", 1);
		
		assertEquals(249.0, checkoutScanner.calculateTotal(), 0.0);
		
		checkoutScanner.emptyCart();
		checkoutScanner.clearItemOfferMap();
		
	}
	
	@Test
	public void testMultipleOffers() {
		
		List<ItemOffer> itemOffersList = new ArrayList<>();
		
		itemOffersList.add(new ItemOffer("atv", 3, 219.0));
		itemOffersList.add(new ItemOffer("ipd", 5, 2499.95));
		itemOffersList.add(new ItemOffer("mbp", 1, 1369.99));
		
		itemOffersList.stream().forEach(item -> checkoutScanner.addItemOffer(item));
		
		checkoutScanner.addItem("atv", 3);
		checkoutScanner.addItem("vga", 1);
		
		assertEquals(249.0, checkoutScanner.calculateTotal(), 0.0);
		
		checkoutScanner.emptyCart();
		checkoutScanner.clearItemOfferMap();
		
	}

}
