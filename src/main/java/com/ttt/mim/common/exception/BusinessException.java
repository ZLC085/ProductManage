package com.ttt.mim.common.exception;

/**
 * 业务异常
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = -4902153677077364421L;

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super("[R_BusinessException]" + message, cause);
    }

    public BusinessException(String message) {
        super("[R_BusinessException]" + message);
    }

    public BusinessException(Throwable cause) {
        super("[R_BusinessException]", cause);
    }

    public BusinessException(String message, Object object) {
        super("[R_BusinessException]" + message + ":" + object);
    }
}
