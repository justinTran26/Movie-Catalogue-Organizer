package CustomExceptions;

/**
 * The ExcessFieldsException class represents an exception that is thrown when excess fields are provided.
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
public class ExcessFieldsException extends Exception {

    /**
     * Constructs an ExcessFieldsException with the specified detail message.
     * 
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ExcessFieldsException(String message) {
        super(message);
    }

    /**
     * Constructs an ExcessFieldsException with the default detail message "Excess fields provided".
     */
    public ExcessFieldsException() {
        super("Excess fields provided");
    }
}
