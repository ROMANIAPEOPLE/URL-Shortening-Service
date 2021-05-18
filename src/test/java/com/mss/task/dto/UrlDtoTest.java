package com.mss.task.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mss.task.controller.dto.UrlDto.UrlConvertingRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UrlDtoTest {

    private UrlConvertingRequest createRequestDtoIncludeHttps() {
        return UrlConvertingRequest.builder()
            .originUrl("https://store.musinsa.com/app/goods/913402")
            .build();
    }

    private UrlConvertingRequest createRequestDtoIncludeTrailingSlash() {
        return UrlConvertingRequest.builder()
            .originUrl("http://store.musinsa.com/app/goods/913402/")
            .build();
    }

    private UrlConvertingRequest createRequestDtoInUppercase() {
        return UrlConvertingRequest.builder()
            .originUrl("HTTP://STORE.MUSINSA.COM/APP/GOODS/913402")
            .build();
    }

    @DisplayName("https:// 프로토콜은 http://로 변환 후 동일한 URL로 처리한다.")
    @Test
    void protocolUnification() {
        UrlConvertingRequest requestDto = createRequestDtoIncludeHttps();

        String originUrl = requestDto.unificationUrl();
        String expected = "http://store.musinsa.com/app/goods/913402";

        assertThat(originUrl).isEqualTo(expected);
    }

    @DisplayName("URL의 마지막에 TRAILING_SLASH가 존재하면 TRAILING_SLASH를 제거한다.")
    @Test
    void removeTrailingSlash() {
        UrlConvertingRequest requestDto = createRequestDtoIncludeTrailingSlash();

        String originUrl = requestDto.unificationUrl();
        String expected = "http://store.musinsa.com/app/goods/913402";

        assertThat(originUrl).isEqualTo(expected);
    }

    @DisplayName("대문자로 입력된 URL은 소문자로 URL로 변환한다.")
    @Test
    void convertToLowercase() {
        UrlConvertingRequest requestDto = createRequestDtoInUppercase();

        String originUrl = requestDto.unificationUrl();
        String expected = "http://store.musinsa.com/app/goods/913402";

        assertThat(originUrl).isEqualTo(expected);
    }
}
