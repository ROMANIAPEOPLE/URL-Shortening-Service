package com.mss.task.service.url;

import com.mss.task.controller.dto.UrlDto.ShortUrlResponse;
import com.mss.task.domain.Url;
import com.mss.task.domain.UrlRepository;
import com.mss.task.exception.UrlNotFoundException;
import com.mss.task.service.encrypt.Base62EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final Base62EncryptionService base62EncryptionService;
    private static final String URL_PREFIX = "http://localhost:8080/";
    private static final String REDIRECT = "redirect:";

    @Transactional
    public ShortUrlResponse getConvertingUrl(String originUrl) {

        originUrl = unificationUrl(originUrl);
        if (urlRepository.existsByOriginUrl(originUrl)) {
            return getShortUrl(originUrl);
        }
        return conversionUrl(originUrl);
    }

    public String redirectToOriginUrl(String shortUrl) {

        Long id = base62EncryptionService.decoding(shortUrl);
        Url url = urlRepository.findById(id).orElseThrow(UrlNotFoundException::new);
        return REDIRECT + url.getOriginUrl();
    }

    private ShortUrlResponse conversionUrl(String originUrl) {

        Url saveUrl = urlRepository.save(Url.from(originUrl));
        String shortUrl = base62EncryptionService.encoding(saveUrl.getId());
        saveUrl.setShortUrl(shortUrl);
        return buildShortUrl(shortUrl);
    }

    private String unificationUrl(String originUrl) {

        originUrl = originUrl.toLowerCase();
        if (originUrl.endsWith("/")) {
            originUrl = originUrl.substring(0, originUrl.length() - 1);
        }
        if (originUrl.startsWith("https")) {
            return originUrl.replace("https", "http");
        }
        return originUrl;
    }

    private ShortUrlResponse getShortUrl(String originUrl) {

        Url url = urlRepository.findByOriginUrl(originUrl).orElseThrow(UrlNotFoundException::new);
        url.increaseCount();
        return buildShortUrl(url.getShortUrl());
    }

    private ShortUrlResponse buildShortUrl(String shortUrl) {

        String url = URL_PREFIX + shortUrl;
        return ShortUrlResponse.from(url);
    }
}
