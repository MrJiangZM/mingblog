package com.blog.mq.config;

public class CannotFindConsumerException extends RuntimeException {

    public CannotFindConsumerException() {
        super();
    }

    public CannotFindConsumerException(String message) {
        super(message);
    }

    public CannotFindConsumerException(String message, Throwable e) {
        super(message, e);
    }

}
