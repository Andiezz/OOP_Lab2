package exception;

public class NullPointerException extends Exception {
    public NullPointerException() {
    }

    public NullPointerException(String message) {
        super(message);
    }

    public NullPointerException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullPointerException(Throwable cause) {
        super(cause);
    }

    public NullPointerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
