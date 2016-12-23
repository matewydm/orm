package pl.edu.agh.wp.orm.exception;

public class ORMUnsupportedTypeConvert extends ORMException {

    public ORMUnsupportedTypeConvert(String message, Throwable cause) {
        super(message, cause);
    }

    public ORMUnsupportedTypeConvert(String message) {
        super(message);
    }

}
