package com.mss.task.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.mss.task.controller.dto.UrlDto.UrlConvertingRequest;
import com.mss.task.domain.Url;
import com.mss.task.domain.UrlRepository;
import com.mss.task.exception.OutOfUrlMemoryException;
import com.mss.task.service.encrypt.EncryptionService;
import com.mss.task.service.url.UrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UrlServiceMockTest {

    private static final Long MAX_ID = 218340105584895L;

    @Mock
    UrlRepository urlRepository;

    @Mock
    EncryptionService base62EncryptionService;

    @InjectMocks
    UrlService urlService;

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

    private Url createOutOfMemoryUrl() {
        return Url.builder()
            .id(MAX_ID + 1)
            .count(1L)
            .originUrl("http://store.musinsa.com/app/goods/1842348")
            .build();
    }

    private UrlConvertingRequest createRequestDto() {
        return UrlConvertingRequest.builder()
            .originUrl("http://store.musinsa.com/app/goods/1842348")
            .build();
    }
}