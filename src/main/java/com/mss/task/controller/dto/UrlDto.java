package com.mss.task.controller.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

public class UrlDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UrlConvertingRequest {

        @URL(message = "http 또는 https를 포함한 올바른 형태의 URL을 입력해주세요.")
        private String originUrl;

        @Builder
        public UrlConvertingRequest(String originUrl) {
            this.originUrl = originUrl;
        }
    }

    @Getter
    public static class ShortUrlResponse {

        private String shortUrl;

        @Builder
        public ShortUrlResponse(String shortUrl) {
            this.shortUrl = shortUrl;
        }

        public static ShortUrlResponse from(String shortUrl) {

            return ShortUrlResponse.builder()
                .shortUrl(shortUrl)
                .build();
        }
    }
}
