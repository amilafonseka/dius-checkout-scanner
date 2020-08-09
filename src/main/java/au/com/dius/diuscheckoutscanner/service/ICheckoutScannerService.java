package au.com.dius.diuscheckoutscanner.service;

import au.com.dius.diuscheckoutscanner.exception.NoSuchItemException;
import au.com.dius.diuscheckoutscanner.exception.QuantityDeficiencyException;

public interface ICheckoutScannerService {

	double calculateTotal();
	
	void addItem(String item, Integer quantity);
	
	void removeItem(String item, Integer quantity) throws NoSuchItemException, QuantityDeficiencyException;
	
	void emptyCart();
}
