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

        private static final int START_INDEX = 0;
        private static String TRAILING_SLASH = "/";

        @URL(message = "http 또는 https를 포함한 올바른 형태의 URL을 입력해주세요.")
        private String originUrl;

        public String unificationUrl() {
            originUrl = originUrl.toLowerCase();
            if (originUrl.endsWith(TRAILING_SLASH)) {
                originUrl = originUrl.substring(START_INDEX, originUrl.length() - 1);
            }
            if (originUrl.startsWith("https")) {
                return originUrl.replace("https", "http");
            }
            return originUrl;
        }

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
