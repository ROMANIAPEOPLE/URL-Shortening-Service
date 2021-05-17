package com.mss.task.service.url;

import com.mss.task.controller.dto.UrlDto.ShortUrlResponse;
import com.mss.task.controller.dto.UrlDto.UrlConvertingRequest;
import com.mss.task.domain.Url;
import com.mss.task.domain.UrlRepository;
import com.mss.task.exception.OutOfUrlMemoryException;
import com.mss.task.exception.UrlNotFoundException;
import com.mss.task.service.encrypt.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UrlService {

    private static final String REDIRECT = "redirect:";
    private static final Long MAX_ID = 218340105584895L;

    private final UrlRepository urlRepository;
    private final EncryptionService base62EncryptionService;

    public String redirectToOriginUrl(String shortUrl) {
        Long id = base62EncryptionService.decoding(shortUrl);
        Url url = urlRepository.findById(id)
            .orElseThrow(() -> new UrlNotFoundException(shortUrl));
        return REDIRECT + url.getOriginUrl();
    }

    @Transactional
    public ShortUrlResponse getConvertingUrl(UrlConvertingRequest requestDto) {
        String originUrl = requestDto.unificationUrl();
        if (urlRepository.existsByOriginUrl(originUrl)) {
            return getShortUrl(originUrl);
        }
        return conversionUrl(originUrl);
    }

    private ShortUrlResponse conversionUrl(String originUrl) {
        Url saveUrl = urlRepository.save(Url.from(originUrl));
        validUrlMemory(saveUrl.getId());
        String shortUrl = base62EncryptionService.encoding(saveUrl.getId());
        saveUrl.setShortUrl(shortUrl);
        return ShortUrlResponse.from(saveUrl.createShortUrl());
    }

    private void validUrlMemory(Long id) {
        if (id > MAX_ID) {
            urlRepository.deleteById(id);
            throw new OutOfUrlMemoryException(id);
        }
    }

    private ShortUrlResponse getShortUrl(String originUrl) {
        Url url = urlRepository.findByOriginUrl(originUrl)
            .orElseThrow(() -> new UrlNotFoundException(originUrl));
        url.increaseCount();
        return ShortUrlResponse.from(url.createShortUrl());
    }
}
