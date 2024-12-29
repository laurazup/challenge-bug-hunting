package exception;

public class InvalidVideoDescriptionException extends RuntimeException{

    public InvalidVideoDescriptionException(String message) {
        super(message);
    }
}
