package Exceptions;

/**
 * Custom exception for when a file couldn't be read
 */
public class FileNotReadException extends RuntimeException {
    public FileNotReadException() {
      super("File could not be read.");
    }

    public FileNotReadException(String message) {
      super(message);
    }
}
