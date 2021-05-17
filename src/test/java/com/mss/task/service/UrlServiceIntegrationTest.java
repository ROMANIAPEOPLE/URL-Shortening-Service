package com.mss.task.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.mss.task.controller.dto.UrlDto.ShortUrlResponse;
import com.mss.task.controller.dto.UrlDto.UrlConvertingRequest;
import com.mss.task.domain.Url;
import com.mss.task.domain.UrlRepository;
import com.mss.task.exception.UrlNotFoundException;
import com.mss.task.service.url.UrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UrlServiceIntegrationTest {

    private static final String URL_PREFIX = "http://localhost:8080/";
    private static final Long MAX_ID = 218340105584895L;

    @Autowired
    private UrlService urlService;

    @Autowired
    private UrlRepository urlRepository;

    private UrlConvertingRequest createRequestDto() {
        return UrlConvertingRequest.builder()
            .originUrl("http://store.musinsa.com/app/goods/1842348")
            .build();
    }

    private UrlConvertingRequest createRequestDtoIncludeHttps() {
        return UrlConvertingRequest.builder()
            .originUrl("https://store.musinsa.com/app/goods/1842348")
            .build();
    }

    private UrlConvertingRequest createRequestDtoIncludeTrailingSlash() {
        return UrlConvertingRequest.builder()
            .originUrl("https://store.musinsa.com/app/goods/1842348/")
            .build();
    }

    private UrlConvertingRequest createRequestDtoIncludeUppercaseUrl() {
        return UrlConvertingRequest.builder()
            .originUrl("HTTPS://STORE.MUSINSA.COM/APP/GOODS/1842348/")
            .build();
    }

    private Url createExistingUrl() {
        return Url.builder()
            .id(1L)
            .count(1L)
            .originUrl("http://store.musinsa.com/app/goods/1842348")
            .shortUrl("ABCD")
            .build();
    }

    @DisplayName("프로토콜을 포함한 정상적인 URL 요청시 성공적으로 URL이 변환된다. (최초 요청)")
    @Test
    void succeedToUrlConversion() {
        UrlConvertingRequest requestDto = createRequestDto();

        ShortUrlResponse convertingUrl = urlService.getConvertingUrl(requestDto);
        Url url = urlRepository.findByOriginUrl(requestDto.getOriginUrl())
            .orElseThrow(() -> new UrlNotFoundException(requestDto.getOriginUrl()));

        assertThat(convertingUrl.getShortUrl()).isEqualTo(URL_PREFIX + url.getShortUrl());
    }

    @DisplayName("https://와 http://는 동일한 URL로 처리하고 기존 URL의 count를 1 증가시킨다.")
    @Test
    void succeedToUrlConversion_unityProtocol() {
        Url existingUrl = urlRepository.save(createExistingUrl());
        UrlConvertingRequest requestDto = createRequestDtoIncludeHttps();

        ShortUrlResponse convertingUrl = urlService.getConvertingUrl(requestDto);

        assertThat(convertingUrl.getShortUrl()).isEqualTo(URL_PREFIX + existingUrl.getShortUrl());
        assertThat(existingUrl.getCount()).isEqualTo(2);
        assertThat(existingUrl.getOriginUrl()).isNotEqualTo(requestDto.getOriginUrl());
    }

    @DisplayName("요청된 URL에 TRAILING_SLASH가 존재하면, 존재하지 않는 URL과 동일한 URL로 처리하고 count를 1 증가시킨다.")
    @Test
    void succeedToUrlConversion_unityTrailingSlash() {
        Url existingUrl = urlRepository.save(createExistingUrl());
        UrlConvertingRequest requestDto = createRequestDtoIncludeTrailingSlash();

        ShortUrlResponse convertingUrl = urlService.getConvertingUrl(requestDto);

        assertThat(convertingUrl.getShortUrl()).isEqualTo(URL_PREFIX + existingUrl.getShortUrl());
        assertThat(existingUrl.getCount()).isEqualTo(2);
        assertThat(existingUrl.getOriginUrl()).isNotEqualTo(requestDto.getOriginUrl());
    }

    @DisplayName("URL에 대문자가 존재하면 소문자로 변환 후, 소문자 URL와 동일한 URL로 처리한다. count를 1 증가시킨다.")
    @Test
    void succeedToUrlConversion_unityAlphabet() {
        Url existingUrl = urlRepository.save(createExistingUrl());
        UrlConvertingRequest requestDto = createRequestDtoIncludeUppercaseUrl();

        ShortUrlResponse convertingUrl = urlService.getConvertingUrl(requestDto);

        assertThat(convertingUrl.getShortUrl()).isEqualTo(URL_PREFIX + existingUrl.getShortUrl());
        assertThat(existingUrl.getCount()).isEqualTo(2);
        assertThat(existingUrl.getOriginUrl()).isNotEqualTo(requestDto.getOriginUrl());
    }
}