package CustomExceptions;

/**
 * The MissingFieldsException class represents an exception that is thrown when required fields are missing.
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
public class MissingFieldsException extends Exception {

    /**
     * Constructs a MissingFieldsException with the specified detail message.
     * 
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public MissingFieldsException(String message) {
        super(message);
    }

    /**
     * Constructs a MissingFieldsException with the default detail message "Missing required fields".
     */
    public MissingFieldsException() {
        super("Missing required fields");
    }
}
