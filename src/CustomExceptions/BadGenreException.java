package CustomExceptions;

/**
 * The BadGenreException class represents an exception that is thrown when an invalid genre format is encountered.
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
public class BadGenreException extends Exception {

    /**
     * Constructs a BadGenreException with the specified detail message.
     * 
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public BadGenreException(String message) {
        super(message);
    }

    /**
     * Constructs a BadGenreException with the default detail message "Invalid genre format".
     */
    public BadGenreException() {
        super("Invalid genre format");
    }
}
