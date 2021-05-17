package com.mss.task.exception;

public class OutOfUrlMemoryException extends RuntimeException {
    public OutOfUrlMemoryException(Long id) {
        super("Out of UrlMemory : " + id);
    }

}
