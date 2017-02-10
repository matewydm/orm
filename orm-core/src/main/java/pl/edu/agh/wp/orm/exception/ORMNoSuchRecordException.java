package pl.edu.agh.wp.orm.exception;

public class ORMNoSuchRecordException extends ORMException {
    public ORMNoSuchRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public ORMNoSuchRecordException(String message) {
        super(message);
    }
}
