package com.rochatec.pos.athena.exception;

/**
 * Created by epr on 02/07/15.
 */
public class FunctionNotExistNException extends I18nException {

    /**
     *
     */
    private static final long serialVersionUID = -456556868656107435L;

    public FunctionNotExistNException() {
        super("function.not.exists.exception");
    }

    public FunctionNotExistNException(String message) {
        super(message);
    }

    public FunctionNotExistNException(String message, Throwable throwable) {
        super(message,throwable);
    }
}
