package com.ttt.mim.common.exception;

/**
 * 数据访问异常
 */
public class DataAccessException extends RuntimeException {

    public DataAccessException() {
        super();
    }

    public DataAccessException(String message, Throwable cause) {
        super("[R_DataAccessException]" + message, cause);
    }

    public DataAccessException(String message) {
        super("[R_DataAccessException]" + message);
    }

    public DataAccessException(Throwable cause) {
        super("[R_DataAccessException]", cause);
    }

    public DataAccessException(String message, Object object) {
        super("[R_DataAccessException]" + message + ":" + object);
    }
}
