package com.warehousemanagement.task.exception;

public class NotFoundArticleException extends RuntimeException{
    public NotFoundArticleException(String message) {
        super(message);
    }
}
