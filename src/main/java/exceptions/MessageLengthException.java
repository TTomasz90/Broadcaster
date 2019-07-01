
/** 
 *  MessageLengthException class
 *  
 *	Description: User defined exception for exceeding SMS message length.
 *
 */

package exceptions;


public class MessageLengthException extends Exception {

	
	private static final long serialVersionUID = 1L;

	/**
	 * Message length exceeded exception
	 * 
	 * @param exceptionMessage
	 */
	public MessageLengthException(String exceptionMessage) {
		super(exceptionMessage);
		System.out.println("Broadcaster Exception");
	}

}
