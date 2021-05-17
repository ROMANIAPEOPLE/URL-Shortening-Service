package com.mss.task.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mss.task.controller.dto.UrlDto.UrlConvertingRequest;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UrlDtoValidationTest {

    private static Validator validator;

    @BeforeAll
    private static void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("잘못된 형식의 URL 입력시 'http 또는 https를 포함한 올바른 형태의 URL을 입력해주세요.' 라는 에러 메시지를 반환한다.")
    @Test
    void urlValidation_error() {
        UrlConvertingRequest requestDto = UrlConvertingRequest.builder()
            .originUrl("www.musinsa.com")
            .build();

        Set<ConstraintViolation<UrlConvertingRequest>> violations = validator.validate(requestDto);

        violations.
            forEach(err -> {
                assertThat(err.getMessage()).isEqualTo("http 또는 https를 포함한 올바른 형태의 URL을 입력해주세요.");
            });
    }

    @DisplayName("올바른 형식의 URL 입력시 비어있는 violations을 반환한다. ")
    @Test
    void urlValidation_succeed() {
        UrlConvertingRequest requestDto = UrlConvertingRequest.builder()
            .originUrl("https://www.musinsa.com")
            .build();

        Set<ConstraintViolation<UrlConvertingRequest>> violations = validator.validate(requestDto);

        assertTrue(violations.isEmpty());
    }
}