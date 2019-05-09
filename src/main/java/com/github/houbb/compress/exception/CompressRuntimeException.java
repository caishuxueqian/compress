package com.github.houbb.compress.exception;

/**
 * 压缩解压运行时异常
 * @author binbin.hou
 * @since 0.0.1
 */
public class CompressRuntimeException extends RuntimeException{

    public CompressRuntimeException() {
    }

    public CompressRuntimeException(String message) {
        super(message);
    }

    public CompressRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompressRuntimeException(Throwable cause) {
        super(cause);
    }

    public CompressRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
