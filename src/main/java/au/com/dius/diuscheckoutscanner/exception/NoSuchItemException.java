package au.com.dius.diuscheckoutscanner.exception;

/**
 * Exeption denoting that there is no such item in the list
 * 
 * @author amilasembunaidelage
 *
 */
public class NoSuchItemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchItemException(String errorMessage) {
		
		super(errorMessage);
		
	}
	
}
