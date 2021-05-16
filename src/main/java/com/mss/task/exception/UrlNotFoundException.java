package com.mss.task.exception;

public class UrlNotFoundException extends RuntimeException {

    public UrlNotFoundException(String url) {
        super("존재하지 않는 Url: " + url);
    }
}
