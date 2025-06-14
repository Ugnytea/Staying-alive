package Exceptions;

/**
 * Author: ugne.stankeviciute@mif.stud.vu.lt
 * Project: Staying-alive
 *
 * ------------------------------------------------------------------------
 * Custom exception for when data couldn't be saved
 */
public class DataNotSavedException extends RuntimeException {
    public DataNotSavedException() {
        super("Data could not be saved.");
    }

    public DataNotSavedException(String message) {
        super(message);
    }
}