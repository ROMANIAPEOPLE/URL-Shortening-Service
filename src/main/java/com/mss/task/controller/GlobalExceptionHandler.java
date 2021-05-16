package com.mss.task.controller;

import com.mss.task.exception.UrlNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<String> handleUrlNotFoundException(UrlNotFoundException exception) {
        log.error("URL 검색 과정에서 오류가 발생했습니다. 다시 시도해주세요.", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("URL 검색 과정에서 오류가 발생했습니다. 다시 시도해주세요.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception exception) {
        log.error("예상치 못한 오류가 발생했습니다. 다시 시도해주세요.", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("예상치 못한 오류가 발생했습니다. 다시 시도해주세요.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String defaultMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("http:// 또는 https://를 포함한 URL을 입력해주세요.", exception);
        return ResponseEntity.badRequest().body(defaultMessage);
    }
}
