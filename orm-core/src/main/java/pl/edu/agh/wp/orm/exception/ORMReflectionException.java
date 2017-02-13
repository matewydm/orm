package pl.edu.agh.wp.orm.exception;

public class ORMReflectionException extends ORMException {
    public ORMReflectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ORMReflectionException(Throwable cause) {
        super(cause);
    }

    public ORMReflectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ORMReflectionException(String message) {
        super(message);
    }

    public ORMReflectionException() {

    }
}
