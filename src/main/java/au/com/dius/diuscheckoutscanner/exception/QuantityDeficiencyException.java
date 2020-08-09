package au.com.dius.diuscheckoutscanner.exception;

/**
 * Exception denoting a quantity deficiency
 * 
 * @author amilasembunaidelage
 *
 */
public class QuantityDeficiencyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuantityDeficiencyException(String errorMessage) {
		
		super(errorMessage);
		
	}
	
}
