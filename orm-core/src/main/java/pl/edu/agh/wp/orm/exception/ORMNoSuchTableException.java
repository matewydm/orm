package pl.edu.agh.wp.orm.exception;

public class ORMNoSuchTableException extends ORMException {
    public ORMNoSuchTableException(String message, Throwable cause){super(message,cause);}
    public ORMNoSuchTableException(String message){
        super(message);
    }
}
