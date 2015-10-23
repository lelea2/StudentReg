package com.util.exception;

/**
 * DAO exception class for Data access layer exception.
 */
public class DAOException extends Exception {

    private static final long serialVersionUID = 1L;

    private static String defaultMsg = "Exception in data access layer";

    /**
     * Constructor for DAOExeception
     * @param String msg
     */
    public DAOException(String msg) {
        super(msg);
    }

    /**
     * Constructor for DAOExeception
     * @param Throwable cause
     */
    public DAOException(Throwable cause) {
        super(defaultMsg, cause);
    }

    /**
     * Constructor for DAOExeception
     * @param msg
     * @param cause
     */
    public DAOException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Default constructor for DAOException
     */
    public DAOException() {
        super(defaultMsg);
    }
}

