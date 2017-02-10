package pl.edu.agh.wp.orm.exception;

public class ReflectionORMException extends ORMException {
    public ReflectionORMException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectionORMException(Throwable cause) {
        super(cause);
    }

    public ReflectionORMException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ReflectionORMException(String message) {
        super(message);
    }

    public ReflectionORMException() {

    }
}
