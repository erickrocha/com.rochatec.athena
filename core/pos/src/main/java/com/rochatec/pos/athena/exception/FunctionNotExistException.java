package com.rochatec.pos.athena.exception;

/**
 * Created by epr on 02/07/15.
 */
public class FunctionNotExistException extends I18Exception {

    /**
     *
     */
    private static final long serialVersionUID = -456556868656107435L;

    public FunctionNotExistException() {
        super("function.not.exists.exception");
    }

    public FunctionNotExistException(String message) {
        super(message);
    }

    public FunctionNotExistException(String message,Throwable throwable) {
        super(message,throwable);
    }
}
