package com.book.util.encoders;

/**
 * Created by samson on 2017/2/28.
 */
public class DecoderException extends IllegalStateException {
    private Throwable cause;

    DecoderException(String msg, Throwable cause) {
        super(msg);
        this.cause = cause;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
