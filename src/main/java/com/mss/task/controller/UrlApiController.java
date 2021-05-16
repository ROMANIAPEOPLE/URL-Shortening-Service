package com.mss.task.controller;

import com.mss.task.controller.dto.UrlDto.ShortUrlResponse;
import com.mss.task.controller.dto.UrlDto.UrlConvertingRequest;
import com.mss.task.service.url.UrlService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlApiController {

    private final UrlService urlService;

    @PostMapping("/url-convert")
    public ShortUrlResponse urlConverting(@RequestBody @Valid UrlConvertingRequest requestDto) {
        return urlService.getConvertingUrl(requestDto);
    }
}
