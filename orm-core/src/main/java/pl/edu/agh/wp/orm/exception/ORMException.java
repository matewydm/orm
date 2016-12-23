package pl.edu.agh.wp.orm.exception;

public class ORMException extends RuntimeException {

    public ORMException(String message, Throwable cause){super(message,cause);}
    public ORMException(String message){
        super(message);
    }

}
