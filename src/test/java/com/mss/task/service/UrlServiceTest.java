package com.mss.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.mss.task.controller.dto.UrlDto.ShortUrlResponse;
import com.mss.task.controller.dto.UrlDto.UrlConvertingRequest;
import com.mss.task.domain.Url;
import com.mss.task.domain.UrlRepository;
import com.mss.task.exception.OutOfUrlMemoryException;
import com.mss.task.service.encrypt.EncryptionService;
import com.mss.task.service.url.UrlService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UrlServiceTest {

    private static final String URL_PREFIX = "http://localhost:8080/";
    private static final Long MAX_ID = 218340105584895L;

    @Mock
    UrlRepository urlRepository;

    @Mock
    EncryptionService base62EncryptionService;

    @InjectMocks
    UrlService urlService;

    private UrlConvertingRequest createRequestDto() {
        return UrlConvertingRequest.builder()
            .originUrl("http://store.musinsa.com/app/goods/1842348")
            .build();
    }

    private Url createNewUrl() {
        return Url.builder()
            .id(1L)
            .count(1L)
            .originUrl("http://store.musinsa.com/app/goods/1842348")
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

    private Url createOutOfMemoryUrl() {
        return Url.builder()
            .id(MAX_ID + 1)
            .count(1L)
            .originUrl("http://store.musinsa.com/app/goods/1842348")
            .build();
    }

    @Test
    @DisplayName("프로토콜을 포함한 정상적인 URL 요청시 성공적으로 URL이 변환된다. (최초 요청)")
    void succeedToUrlConversion() {
        UrlConvertingRequest requestDto = createRequestDto();
        Url url = createNewUrl();
        String shortUrl = "B";

        given(urlRepository.existsByOriginUrl(any(String.class))).willReturn(false);
        given(urlRepository.save(any(Url.class))).willReturn(url);
        given(base62EncryptionService.encoding(any(Long.class))).willReturn(shortUrl);
        ShortUrlResponse convertingUrl = urlService.getConvertingUrl(requestDto);

        assertThat(convertingUrl.getShortUrl().endsWith(shortUrl)).isEqualTo(true);
        assertThat(convertingUrl.getShortUrl()).isEqualTo(URL_PREFIX + shortUrl);
        verify(urlRepository, atLeastOnce()).existsByOriginUrl(any(String.class));
        verify(urlRepository, atLeastOnce()).save(any(Url.class));
    }

    @Test
    @DisplayName("이전에 요청했던 URL은 동일한 short URL을 요청하고 요청 횟수를 1만큼 증가시킨다.")
    void succeedToUrlConversion_duplicate() {
        UrlConvertingRequest requestDto = createRequestDto();
        Url existingUrl = createExistingUrl();

        given(urlRepository.existsByOriginUrl(any(String.class))).willReturn(true);
        given(urlRepository.findByOriginUrl(any(String.class)))
            .willReturn(Optional.of(existingUrl));
        ShortUrlResponse convertingUrl = urlService.getConvertingUrl(requestDto);

        assertThat(convertingUrl.getShortUrl()).isEqualTo(URL_PREFIX + existingUrl.getShortUrl());
        assertThat(existingUrl.getCount()).isEqualTo(createExistingUrl().getCount() + 1);
        verify(urlRepository, atLeastOnce()).existsByOriginUrl(any(String.class));
        verify(urlRepository, atLeastOnce()).findByOriginUrl(any(String.class));

    }

    @Test
    @DisplayName("변환된 URL의 길이가 8 Character를 초과한다면 OutOfUrlMemory exception이 발생한다.")
    void exceededUrlMemory() {
        UrlConvertingRequest requestDto = createRequestDto();
        Url url = createOutOfMemoryUrl();

        given(urlRepository.existsByOriginUrl(any(String.class))).willReturn(false);
        given(urlRepository.save(any(Url.class))).willReturn(url);

        assertThrows(OutOfUrlMemoryException.class, () -> urlService.getConvertingUrl(requestDto));
        verify(urlRepository, atLeastOnce()).existsByOriginUrl(any(String.class));
        verify(urlRepository, atLeastOnce()).save(any(Url.class));
        verify(urlRepository, atLeastOnce()).deleteById(any(Long.class));

    }

}