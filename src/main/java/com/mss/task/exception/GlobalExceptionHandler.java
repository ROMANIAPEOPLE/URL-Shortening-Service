package com.mss.task.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity handleUrlNotFoundException(UrlNotFoundException exception) {
        log.error("URL 검색 과정에서 오류가 발생했습니다. 다시 시도해주세요.", exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
