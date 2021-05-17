package com.mss.task.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UrlTest {

    private static final String URL_PREFIX = "http://localhost:8080/";

    private Url createUrl() {
        return Url.builder()
            .id(1L)
            .originUrl("http://store.musinsa.com/app/goods/1842348")
            .shortUrl("ABCD")
            .count(1L)
            .build();
    }

    @DisplayName("기존 URL 객체의 count가 1 증가한다.")
    @Test
    void increaseCount() {
        Url url = createUrl();

        url.increaseCount();

        assertThat(url.getCount()).isEqualTo(2L);
    }

    @DisplayName("URL 객체에 저장된 shortUrl을 이용해 Converting 작업이 완료된 URL을 반환한다.")
    @Test
    void createShortUrl() {
        Url url = createUrl();

        String convertingUrl = url.createShortUrl();

        assertThat(convertingUrl).isEqualTo(URL_PREFIX + url.getShortUrl());
    }
}