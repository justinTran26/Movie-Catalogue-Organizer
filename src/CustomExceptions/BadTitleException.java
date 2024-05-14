package CustomExceptions;

/**
 * The BadTitleException class represents an exception that is thrown when an invalid title format is encountered.
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
public class BadTitleException extends Exception {

    /**
     * Constructs a BadTitleException with the specified detail message.
     * 
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public BadTitleException(String message) {
        super(message);
    }

    /**
     * Constructs a BadTitleException with the default detail message "Invalid title format".
     */
    public BadTitleException() {
        super("Invalid title format");
    }
}
