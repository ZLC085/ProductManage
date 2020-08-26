package com.ttt.mim.common.exception;

/**
 * 数据不存在异常
 */
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message, Throwable cause) {
        super("[R_DataNotFoundException]" + message, cause);
    }

    public DataNotFoundException(String message) {
        super("[R_DataNotFoundException]" + message);
    }

    public DataNotFoundException(Throwable cause) {
        super("[R_DataNotFoundException]", cause);
    }
}
