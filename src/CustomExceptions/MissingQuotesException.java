
package CustomExceptions;

/**
 * The MissingQuotesException class represents an exception that is thrown when quotation marks are missing.
 * This exception extends the Exception class.
 * 
 * Nicole Koran (40281430) and Justin Tran (40281429)
 * COMP249
 * Assignment #2
 * Date Due: Mar 27, 2024
 * 
 * @author Nicole Koran
 * @author Justin Tran
 * @version 1.0
 */
public class MissingQuotesException extends Exception {

    /**
     * Constructs a MissingQuotesException with the specified detail message.
     * 
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public MissingQuotesException(String message) {
        super(message);
    }

    /**
     * Constructs a MissingQuotesException with the default detail message "Missing quotation marks".
     */
    public MissingQuotesException() {
        super("Missing quotation marks");
    }
}
