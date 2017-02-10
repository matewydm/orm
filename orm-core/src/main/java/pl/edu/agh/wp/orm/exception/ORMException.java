package pl.edu.agh.wp.orm.exception;

    public class ORMException extends RuntimeException {

    public ORMException(String message, Throwable cause){super(message,cause);}

        public ORMException(Throwable cause) {
            super(cause);
        }

        public ORMException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

        public ORMException(String message){
        super(message);
    }

        public ORMException() {
        }
    }
