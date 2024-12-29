package exception;

public class InvalidVideoDurationException extends RuntimeException {

    public InvalidVideoDurationException(String message) {
        super(message);
    }
}
