package Backend;

// Custom exception for when data couldn't be saved
public class DataNotSavedException extends RuntimeException {
    public DataNotSavedException(String message) {
        super(message);
    }
}

// Custom exception for when a file couldn't be read
class FileNotReadException extends RuntimeException {
    public FileNotReadException(String message) {
        super(message);
    }
}


